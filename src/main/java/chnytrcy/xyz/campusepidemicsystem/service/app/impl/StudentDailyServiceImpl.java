package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentDailyMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.studentdalily.QueryStudentDailyCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.StudentDaily;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.studentdaily.QueryStudentDailyVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StudentDailyService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
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
