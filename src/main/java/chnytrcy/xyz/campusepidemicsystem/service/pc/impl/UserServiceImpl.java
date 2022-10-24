package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.config.exception.UserAuthenticationException;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.ShiroService;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.utils.HttpContextUtil;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.TeacherMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.UserMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.AddUserCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.ChangePwdCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.LoginCommand;
import chnytrcy.xyz.campusepidemicsystem.model.constance.LoginMethodConstance;
import chnytrcy.xyz.campusepidemicsystem.model.dto.AdminInformationDTO;
import chnytrcy.xyz.campusepidemicsystem.model.dto.EpidemicInformationDTO;
import chnytrcy.xyz.campusepidemicsystem.model.dto.StudentInformationDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.Role;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.model.enums.AuthenticationError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.SuccessReturnCode;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.RoleEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.TeacherEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.UserService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.md5.MD5;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务类
 * @Author chnytrcy
 * @DATE 2022/8/21 11:18 AM
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Autowired private ShiroService shiroService;

  @Autowired private HttpContextUtil httpContextUtil;

  @Autowired private UserMapper userMapper;

  @Autowired private TeacherMapper teacherMapper;

  @Autowired private StudentMapper studentMapper;

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public Result<Void> addUser(AddUserCommand command) {
    String password = MD5.SysMd5(command.getAccount(), command.getPassword());
    User user = new User();
    user.setAccount(command.getAccount());
    user.setPassword(password);
    user.setPhone("18358889334");
    user.setCreateUser("superAdmin");
    user.setUpdateUser("superAdmin");
    int insert = getBaseMapper().insert(user);
    if(insert > 0){
      return ResultFactory.successResult();
    }
    throw new BusinessException(BusinessError.USER_ADD_ERROR);
  }

  @Override
  public Result login(LoginCommand command,Integer i) {
    User user = shiroService.findByUsername(command.getAccount());
    if(ObjectUtil.isNull(user)){
      throw new UserAuthenticationException(AuthenticationError.LOGIN_ACCOUNT_NOT_EXIST_ERROR);
    }
    String password = MD5.SysMd5(String.valueOf(user.getAccount()),command.getPassword());
    if(!user.getPassword().equals(password)){
      throw new UserAuthenticationException(AuthenticationError.LOGIN_PASSWORD_ERROR);
    }else {
      List<Role> roleList = userMapper.findRoleListByUserId(user.getId());
      if(roleList.isEmpty()){
        throw new UserAuthenticationException(AuthenticationError.ROLE_UNKNOWN_ERROR);
      }
      String roleName = roleList.get(0).getRoleName();
      if(Arrays.asList(
          RoleEnums.TEACHER.getDesc(),
          RoleEnums.STUDENT.getDesc(),
          RoleEnums.QUARANTINE.getDesc()).contains(roleName) && LoginMethodConstance.PC.equals(i)){
        throw new UserAuthenticationException(AuthenticationError.PC_UNALLOW_TYPE_ERROR);
      }
      if(Arrays.asList(
          RoleEnums.ADMIN.getDesc(),
          RoleEnums.TEACHER.getDesc()).contains(roleName) && LoginMethodConstance.MOBILE.equals(i)){
        throw new UserAuthenticationException(AuthenticationError.MOBILE_UNKNOWN_TYPE_ERROR);
      }
      String token = shiroService.createToken(user,i);
      HashMap<String,Object> map = new HashMap<>();
      map.put("token",token);
      map.put("userType",roleName);
      fillUser(map,roleName,user);
      return ResultFactory.successResult(map);
    }
  }

  @Override
  public Result loginOut() {
    String token = httpContextUtil.getToken();
    return shiroService.logout(token);
  }

  @Override
  public Result changePwd(ChangePwdCommand command) {
    if(command.getPassword().equals(command.getPasswordRepeat())){
      throw new UserAuthenticationException(AuthenticationError.PASSWORD_NOT_SAME_ERROR);
    }
    Long userId = httpContextUtil.getUserId();
    User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
        .eq(User::getId, userId));
    if(Objects.isNull(user)){
      throw new UserAuthenticationException(AuthenticationError.LOGIN_ACCOUNT_NOT_EXIST_ERROR);
    }
    String s = MD5.SysMd5(user.getAccount(), command.getPassword());
    if(s.equals(user.getPassword())){
      throw new UserAuthenticationException(AuthenticationError.PASSWORD_SAME_WITH_OLD_ERROR);
    }
    user.setPassword(s);
    userMapper.updateById(user);
    //登出
    loginOut();
    return ResultFactory.successResult(SuccessReturnCode.CHANGE_PASSWORD);
  }

  /**
   * 填充用户基本信息
   * @param map
   * @param roleName
   * @param user
   */
  public void fillUser(Map<String,Object> map,String roleName,User user){
    if(roleName.equals(RoleEnums.ADMIN.getName())){
      AdminInformationDTO dto = getBaseMapper().queryAdminInformation(user);
      if(ObjectUtil.isNull(dto)){
        throw new UserAuthenticationException(AuthenticationError.ADMIN_UNKNOWN_INFORMATION_ERROR);
      }
      map.put("information",dto);
    }else if(roleName.equals(RoleEnums.EPIDEMIC_PREVENTION.getName())){
      Teacher teacher = teacherMapper.selectOne(
          new LambdaQueryWrapper<Teacher>()
              .eq(Teacher::getCode, user.getAccount())
              .eq(Teacher::getEpidemicMark, TeacherEnums.EPIDEMIC_MARK_YES.getCode()));
      if(ObjectUtil.isNull(teacher)){
        throw new UserAuthenticationException(AuthenticationError.EPIDEMIC_UNKNOWN_INFORMATION_ERROR);
      }
      EpidemicInformationDTO convert = DozerUtils.convert(teacher, EpidemicInformationDTO.class);
      map.put("information",convert);
    }else if(roleName.equals(RoleEnums.STUDENT.getName()) ||
        roleName.equals(RoleEnums.QUARANTINE.getName())){
      Student student = studentMapper.selectOne(
          new LambdaQueryWrapper<Student>()
              .eq(Student::getCode, user.getAccount()));
      if(ObjectUtil.isNull(student)){
        throw new UserAuthenticationException(AuthenticationError.STUDENT_UNKNOWN_INFORMATION_ERROR);
      }
      StudentInformationDTO convert = DozerUtils.convert(student, StudentInformationDTO.class);
      map.put("information",convert);
    }
  }
}
