package xyz.chnytrcy.campusepidemicsystem.common;

import javax.annotation.Resource;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.config.shiro.utils.HttpContextUtil;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.common
 * @ClassName: StudentCommon
 * @Author: ChnyTrcy
 * @Description: 学生Common
 * @Date: 2022/8/29 5:06 PM
 * @Version: 1.0
 */
@Component
public class StudentCommon {

  @Autowired private StudentMapper studentMapper;

  @Resource private HttpContextUtil httpContextUtil;

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

  public Student getStudent(){
    Student student = studentMapper.selectOne(new LambdaQueryWrapper<Student>()
        .eq(Student::getCode,httpContextUtil.getUsername()));
    if(Objects.isNull(student)){
      throw new BusinessException(BusinessError.STUDENT_IS_NOT_EXIST_ERROR);
    }
    return student;
  }

}
