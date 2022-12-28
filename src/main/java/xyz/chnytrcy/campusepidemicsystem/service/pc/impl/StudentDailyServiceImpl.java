package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentDailyMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.studentdalily.QueryStudentDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.StudentDaily;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.studentdaily.QueryStudentDailyVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.StudentDailyService;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
 * @ClassName: StudentDailyServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/10/7 13:38
 * @Version: 1.0
 */
@Service
public class StudentDailyServiceImpl extends ServiceImpl<StudentDailyMapper, StudentDaily>
    implements StudentDailyService {

  @Override
  public Result<List<QueryStudentDailyVO>> queryStudentDaily(QueryStudentDailyCommand command) {
    if(LocalDateTime.now().getYear() < command.getYear() ||
        (LocalDateTime.now().getYear() == command.getYear() &&
        LocalDateTime.now().getMonthValue() < command.getMonth())){
      throw new BusinessException(BusinessError.STUDENT_DAILY_YEAR_AFTER_NOW_ERROR);
    }
    LocalDateTime startTime = LocalDateTime.of(command.getYear(), command.getMonth(),1,0,0);
    LocalDateTime endTime = null;
    if(command.getMonth() == 12){
      endTime = LocalDateTime.of(command.getYear() + 1, 1,1,0,0);
    }else {
      endTime = LocalDateTime.of(command.getYear(),command.getMonth() + 1,1,0,0);
    }
    List<StudentDaily> studentDailies = getBaseMapper().selectList(
        new LambdaQueryWrapper<StudentDaily>()
            .eq(StudentDaily::getRelId, command.getId())
            .ge(StudentDaily::getCreateTime,startTime)
            .lt(StudentDaily::getCreateTime,endTime));
    List<QueryStudentDailyVO> queryStudentDailyVOS = DozerUtils.convertList(studentDailies,
        QueryStudentDailyVO.class);
    return ResultFactory.successResult(queryStudentDailyVOS);
  }
}
