package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import chnytrcy.xyz.campusepidemicsystem.common.StudentCommon;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentDailyMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.studentdaily.AddStudentDailyCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.StudentDaily;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.StudentDailyEnums;
import chnytrcy.xyz.campusepidemicsystem.service.app.StudentDailyAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
 * @ClassName: StudentDailyAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/10/7 10:30
 * @Version: 1.0
 */
@Service
public class StudentDailyAppServiceImpl extends ServiceImpl<StudentDailyMapper, StudentDaily>
    implements StudentDailyAppService {

  private static final String DATA_FORMAT = "yyyyMMdd";

  @Autowired private StudentDailyMapper studentDailyMapper;

  @Autowired private StudentCommon studentCommon;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> addStudentDaily(AddStudentDailyCommand command) {
    Long studentId = studentCommon.getStudentID();
    List<StudentDaily> studentDailies = studentDailyMapper.selectList(
        new LambdaQueryWrapper<StudentDaily>()
            .eq(StudentDaily::getRelId, studentId)
            .orderByDesc(StudentDaily::getCreateTime)
            .last("limit 1"));
    if(!studentDailies.isEmpty()){
      LocalDateTime createTime = studentDailies.get(0).getCreateTime();
      if (createTime.toLocalDate().isEqual(LocalDate.now())) {
        throw new BusinessException(BusinessError.STUDENT_DAILY_AGAIN_ERROR);
      }
    }
    if(command.getPromise().equals(StudentDailyEnums.PROMISE_NO.getNumber())){
      throw new BusinessException(BusinessError.STUDENT_DAILY_PROMISE_NO_ERROR);
    }
    StudentDaily convert = DozerUtils.convert(command, StudentDaily.class);
    convert.setRelId(studentId);
    getBaseMapper().insert(convert);
    return ResultFactory.successResult();
  }
}
