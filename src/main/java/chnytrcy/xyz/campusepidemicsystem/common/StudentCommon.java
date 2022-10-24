package chnytrcy.xyz.campusepidemicsystem.common;

import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.config.exception.UserAuthenticationException;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.utils.HttpContextUtil;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.UserMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.model.enums.AuthenticationError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: StudentCommon
 * @Author: ChnyTrcy
 * @Description: 学生Common
 * @Date: 2022/8/29 5:06 PM
 * @Version: 1.0
 */
@Component
public class StudentCommon {

  @Autowired private StudentMapper studentMapper;

  @Autowired private HttpContextUtil httpContextUtil;

  @Autowired private UserMapper userMapper;

  /**
   * 以学生学号为关键字，映射为Map
   */
  public Map<String, Student> studentHashMap(){
    List<Student> students = studentMapper.selectList(null);
    return students.stream()
        .collect(Collectors.toMap(Student::getCode,e -> e));
  }

  public String getStudentCode(){
    Student student = studentMapper.selectOne(
        new LambdaQueryWrapper<Student>()
            .eq(Student::getCode, httpContextUtil.getUsername()));
    if(Objects.isNull(student)){
      throw new BusinessException(BusinessError.STUDENT_IS_NOT_EXIST_ERROR);
    }
    return student.getCode();
  }

  public Long getStudentID(){
    Student student = studentMapper.selectOne(
        new LambdaQueryWrapper<Student>()
            .eq(Student::getCode, httpContextUtil.getUsername()));
    if(Objects.isNull(student)){
      throw new BusinessException(BusinessError.STUDENT_IS_NOT_EXIST_ERROR);
    }
    return student.getId();
  }

}
