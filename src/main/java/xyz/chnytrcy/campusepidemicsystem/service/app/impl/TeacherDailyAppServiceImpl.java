package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.chnytrcy.campusepidemicsystem.common.TeacherCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.TeacherDailyMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.teacherdaily.DownDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.teacherdaily.UpDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import xyz.chnytrcy.campusepidemicsystem.model.entity.TeacherDaily;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.TeacherDailyEnums;
import xyz.chnytrcy.campusepidemicsystem.service.app.TeacherDailyAppService;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
 * @ClassName: TeacherDailyAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/6 11:21 AM
 * @Version: 1.0
 */
@Service
public class TeacherDailyAppServiceImpl extends ServiceImpl<TeacherDailyMapper, TeacherDaily>
    implements TeacherDailyAppService {

  @Autowired private TeacherCommon teacherCommon;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> upDaily(UpDailyCommand command) {
    Teacher teacher = checkTeacher();
    List<TeacherDaily> teacherDailyList = getBaseMapper().selectList(
        new LambdaQueryWrapper<TeacherDaily>()
            .eq(TeacherDaily::getCode, teacher.getCode())
            .orderByDesc(TeacherDaily::getCreateTime));
    teacherDailyList.forEach(e -> {
      if(e.getCreateTime().toLocalDate().isEqual(LocalDate.now()) &&
          e.getType().equals(TeacherDailyEnums.TYPE_UP.getNumber())){
        throw new BusinessException(BusinessError.TEACHER_DAILY_UP_AGAIN_ERROR);
      }
    });
    TeacherDaily teacherDaily = new TeacherDaily();
    teacherDaily.setCode(teacher.getCode());
    teacherDaily.setType(TeacherDailyEnums.TYPE_UP.getNumber());
    teacherDaily.setResult(command.getResult());
    getBaseMapper().insert(teacherDaily);
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> downDaily(DownDailyCommand command) {
    Teacher teacher = checkTeacher();
    List<TeacherDaily> teacherDailyList = getBaseMapper().selectList(
        new LambdaQueryWrapper<TeacherDaily>()
            .eq(TeacherDaily::getCode, teacher.getCode())
            .orderByDesc(TeacherDaily::getCreateTime));
    teacherDailyList.forEach(e -> {
      if(e.getCreateTime().toLocalDate().isEqual(LocalDate.now()) &&
          e.getType().equals(TeacherDailyEnums.TYPE_DOWN.getNumber())){
        throw new BusinessException(BusinessError.TEACHER_DAILY_DOWN_AGAIN_ERROR);
      }
    });
    TeacherDaily teacherDaily = new TeacherDaily();
    teacherDaily.setCode(teacher.getCode());
    teacherDaily.setType(TeacherDailyEnums.TYPE_DOWN.getNumber());
    getBaseMapper().insert(teacherDaily);
    return ResultFactory.successResult();
  }

  private Teacher checkTeacher(){
    Teacher teacher = teacherCommon.getTeacher();
    if(Objects.isNull(teacher)){
      throw new BusinessException(BusinessError.TEACHER_IS_NOT_EXIST_ERROR);
    }
    return teacher;
  }
}
