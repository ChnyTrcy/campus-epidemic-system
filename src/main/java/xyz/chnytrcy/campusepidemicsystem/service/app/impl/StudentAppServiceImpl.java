package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import static xyz.chnytrcy.campusepidemicsystem.model.constance.IsolationPersonConstance.QUARANTINE_DAYS;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.common.StudentCommon;
import xyz.chnytrcy.campusepidemicsystem.common.TeacherCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationDetailMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationPersonMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.student.ScanCodeGetStudentInformationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationDetail;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.student.GetUserIdVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.student.ScanCodeGetStudentInformationVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.StudentAppService;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
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
