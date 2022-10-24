package chnytrcy.xyz.campusepidemicsystem.common;

import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.utils.HttpContextUtil;
import chnytrcy.xyz.campusepidemicsystem.mapper.TeacherMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.TeacherEnums;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: TeacherCommon
 * @Author: ChnyTrcy
 * @Description: 教师常用类
 * @Date: 2022/8/30 5:23 PM
 * @Version: 1.0
 */
@Component
public class TeacherCommon {

  @Autowired private HttpContextUtil httpContextUtil;

  @Autowired private TeacherMapper teacherMapper;

  /**
   * 获得教职工对象
   */
  public Teacher getTeacher(){
    return teacherMapper.selectOne(new LambdaQueryWrapper<Teacher>()
        .eq(Teacher::getId, httpContextUtil.getUserId()));
  }

  /**
   * 获得教职工对应的院系编号
   */
  public String getTeacherDeptCode(){
    Teacher teacher = getTeacher();
    if(null == teacher) {
      throw new BusinessException(BusinessError.TEACHER_IS_NOT_EXIST_ERROR);
    }
    return teacher.getDeptCode();
  }

  /**
   * 获得防疫人员的院系编号
   */
  public String getEpidemicDeptCode(){
    Teacher epidemic = getEpidemic();
    return epidemic.getDeptCode();
  }

  /**
   * 获得并检验登陆的防疫人员账号合法性
   */
  public Teacher getEpidemic(){
    Teacher teacher = teacherMapper.selectOne(
        new LambdaQueryWrapper<Teacher>()
            .eq(Teacher::getId, httpContextUtil.getUserId()));
    if(null == teacher) {
      throw new BusinessException(BusinessError.TEACHER_IS_NOT_EXIST_ERROR);
    }
    if(teacher.getEpidemicMark().equals(TeacherEnums.EPIDEMIC_MARK_NO.getNumber())){
      throw new BusinessException(BusinessError.TEACHER_NOT_EPIDEMIC_ERROR);
    }
    return teacher;
  }

}
