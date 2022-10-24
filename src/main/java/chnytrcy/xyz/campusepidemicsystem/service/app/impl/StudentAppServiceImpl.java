package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import static chnytrcy.xyz.campusepidemicsystem.model.constance.IsolationPersonConstance.QUARANTINE_DAYS;

import chnytrcy.xyz.campusepidemicsystem.common.StudentCommon;
import chnytrcy.xyz.campusepidemicsystem.common.TeacherCommon;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationPersonMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.student.ScanCodeGetStudentInformationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationDetail;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.student.GetUserIdVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.student.ScanCodeGetStudentInformationVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.StudentAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
 * @ClassName: StudentAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/12 2:45 PM
 * @Version: 1.0
 */
@Service
public class StudentAppServiceImpl extends ServiceImpl<StudentMapper,Student>
    implements StudentAppService {

  @Value("${qr.code.expiration.time}")
  private Long expirationTime;

  @Autowired private IsolationPersonMapper isolationPersonMapper;

  @Autowired private IsolationDetailMapper isolationDetailMapper;

  @Autowired private StudentCommon studentCommon;

  @Autowired private TeacherCommon teacherCommon;

  @Override
  public Result<GetUserIdVO> getUserId() {
    GetUserIdVO getUserIdVO = new GetUserIdVO(String.valueOf(studentCommon.getStudentID()),LocalDateTime.now());
    return ResultFactory.successResult(getUserIdVO);
  }

  @Override
  public Result<ScanCodeGetStudentInformationVO> scanCodeGetStudentInformation(ScanCodeGetStudentInformationCommand command) {
    //获得教职工信息
    Teacher teacher = teacherCommon.getEpidemic();
    if(command.getTime().plusMinutes(expirationTime).isBefore(LocalDateTime.now())){
      throw new BusinessException(BusinessError.EPIDEMIC_PREVENTION_QR_CODE_INVALID_ERROR);
    }
    //获得学生信息
    Student student = getBaseMapper().selectOne(
        new LambdaQueryWrapper<Student>()
            .eq(Student::getId, command.getId()));
    if(!teacher.getDeptCode().equals(student.getDeptCode())){
      throw new BusinessException(BusinessError.ISOLATION_PERSON_NOT_IN_EPIDEMIC_DEPT_ERROR);
    }
    ScanCodeGetStudentInformationVO vo = DozerUtils.convert(student,
        ScanCodeGetStudentInformationVO.class);
    //是否未隔离
    IsolationPerson isolationPerson = isolationPersonMapper.selectOne(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getCode,student.getCode())
            .eq(IsolationPerson::getState,
                IsolationPersonEnums.STATE_NOTIFICATION_NOT_QUARANTINED.getCode()));
    if(Objects.isNull(isolationPerson)){
      vo.setIsolationOperation(false);
      vo.setAddTemperature(false);
      vo.setReleaseQuarantine(false);
      return ResultFactory.successResult(vo);
    }
    vo.setIsolationOperation(true);

    IsolationPerson allIsolationPerson = isolationPersonMapper.selectOne(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getCode,student.getCode()));
    //体温记录是否小于14
    List<IsolationDetail> list = isolationDetailMapper.selectList(
        new LambdaQueryWrapper<IsolationDetail>()
            .eq(IsolationDetail::getRelId, allIsolationPerson.getId()));
    if(list.size() < QUARANTINE_DAYS){
      vo.setAddTemperature(true);
      vo.setReleaseQuarantine(false);
      return ResultFactory.successResult(vo);
    }
    //是否是治疗中状态
    vo.setAddTemperature(false);
    vo.setReleaseQuarantine(
        !allIsolationPerson.getState().equals(IsolationPersonEnums.STATE_TREAT.getCode()));
    return ResultFactory.successResult(vo);
  }
}
