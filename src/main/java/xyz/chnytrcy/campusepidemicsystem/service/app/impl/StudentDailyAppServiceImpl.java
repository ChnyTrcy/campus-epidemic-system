package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.chnytrcy.campusepidemicsystem.common.StudentCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentDailyMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.studentdaily.AddStudentDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.StudentDaily;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.StudentDailyEnums;
import xyz.chnytrcy.campusepidemicsystem.service.app.StudentDailyAppService;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
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
