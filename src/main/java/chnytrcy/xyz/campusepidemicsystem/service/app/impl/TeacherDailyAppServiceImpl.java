package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import chnytrcy.xyz.campusepidemicsystem.common.TeacherCommon;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.TeacherDailyMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.teacherdaily.DownDailyCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.teacherdaily.UpDailyCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.entity.TeacherDaily;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.TeacherDailyEnums;
import chnytrcy.xyz.campusepidemicsystem.service.app.TeacherDailyAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
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
