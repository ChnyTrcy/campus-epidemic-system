package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import static xyz.chnytrcy.campusepidemicsystem.model.constance.RedisPrefixConstance.LOGIN_CAPTCHA_SMS_PREFIX;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhenzi.sms.ZhenziSmsClient;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.chnytrcy.campusepidemicsystem.config.shiro.ShiroService;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.TeacherMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.AddUserCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.ChangePwdCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.ForgetPasswordCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.LoginCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.PhoneMessageCaptchaCommand;
import xyz.chnytrcy.campusepidemicsystem.model.dto.AdminInformationDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.CaptchaDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.EpidemicInformationDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.PhoneMessageCaptchaDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.StudentInformationDTO;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.Role;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.User;
import xyz.chnytrcy.campusepidemicsystem.model.enums.AuthenticationError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.LoginTypeEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.SuccessReturnCode;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.RoleEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.TeacherEnums;
import xyz.chnytrcy.campusepidemicsystem.service.pc.UserService;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.config.exception.UserAuthenticationException;
import xyz.chnytrcy.core.config.shiro.utils.HttpContextUtil;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.md5.MD5;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;
import xyz.chnytrcy.core.utils.sms.ZhenziSmsUtil;

/**
 * @ClassName UserServiceImpl
 * @Description 用户服务类
 * @Author chnytrcy
 * @DATE 2022/8/21 11:18 AM
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

  @Value("${login.captcha.switch}")
  private Boolean captchaSwitch;

  @Value("${login.sms.captcha.switch}")
  private Boolean smsCaptchaSwitch;

  @Value("${login.captcha.time}")
  private Long captchaTime;

  @Value("${login.sms.captcha.time}")
  private Long phoneCaptchaTime;

  @Value("${login.sms.captcha.limit}")
  private Boolean phoneCaptchaLimit;

  @Value("${login.captcha.auto}")
  private Boolean captchaAuto;

  @Value("${login.captcha.auto.number}")
  private Integer captchaAutoNumber;

  private static final String SESSION_CAPTCHA_NAME = "captcha";

  private static final String SESSION_CAPTCHA_AUTO_ID = "captchaAutoId_";

  /**
   * 短信模版ID
   */
  private static final String SMS_TEMPLATE_CODE = "10728";

  private static final String SMS_TEMPLATE_FORGET_CODE = "10895";

  @Autowired private RedisTemplate redisTemplate;

  @Autowired private ZhenziSmsUtil smsUtil;

  @Autowired private ShiroService shiroService;

  @Resource private HttpContextUtil httpContextUtil;

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
  public Result login(LoginCommand command, LoginTypeEnums type,HttpServletRequest request) {
    captchaPrepositionValidate(command,type,request);
    if(type.equals(LoginTypeEnums.PC_PHONE)){
      loginByPhone(command);
    }else {
      loginByPassword(command,request);
    }
    User user = shiroService.findByUsername(command.getAccount());
    List<Role> roleList = userMapper.findRoleListByUserId(user.getId());
    if(roleList.isEmpty()){
      throw new UserAuthenticationException(AuthenticationError.ROLE_UNKNOWN_ERROR);
    }
    String roleName = roleList.get(0).getRoleName();
    if(Arrays.asList(
        RoleEnums.TEACHER.getDesc(),
        RoleEnums.STUDENT.getDesc(),
        RoleEnums.QUARANTINE.getDesc()).contains(roleName) && (
        type.equals(LoginTypeEnums.PC_PASSWORD) || type.equals(LoginTypeEnums.PC_PHONE)
    )){
      throw new UserAuthenticationException(AuthenticationError.PC_UNALLOW_TYPE_ERROR);
    }
    if(Arrays.asList(
        RoleEnums.ADMIN.getDesc()).contains(roleName) && type.equals(LoginTypeEnums.APP_PASSWORD)){
      throw new UserAuthenticationException(AuthenticationError.MOBILE_UNKNOWN_TYPE_ERROR);
    }
    String token = shiroService.createToken(user,type);
    HashMap<String,Object> map = new HashMap<>();
    map.put("token",token);
    map.put("userType",roleName);
    map.put("loginType",type.getDesc());
    fillUser(map,roleName,user);
    return ResultFactory.successResult(map);
  }

  @Override
  public Result loginOut() {
    String token = httpContextUtil.getToken();
    return shiroService.logout(token);
  }

  @Override
  @Transactional(rollbackFor = UserAuthenticationException.class)
  public Result changePwd(ChangePwdCommand command) {
    if(!command.getPassword().equals(command.getPasswordRepeat())){
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

  @Override
  public Result<Void> getPhoneMessageCaptcha(PhoneMessageCaptchaCommand command) {
    if(smsCaptchaSwitch.equals(Boolean.FALSE)){
      throw new UserAuthenticationException(AuthenticationError.SMS_SWITCH_CLOSE_ERROR);
    }
    String s = MD5.SysMd5(LOGIN_CAPTCHA_SMS_PREFIX, command.getPhone());
    //1、检查是否有重复的redis体
    PhoneMessageCaptchaDTO o = (PhoneMessageCaptchaDTO) redisTemplate.opsForValue().get(
        LOGIN_CAPTCHA_SMS_PREFIX + s);
    if(ObjectUtil.isNotNull(o)){
      if(o.getStartTime().plusSeconds(o.getDurationTime()).isAfter(LocalDateTime.now()) || !phoneCaptchaLimit){
        throw new UserAuthenticationException(AuthenticationError.SMS_MESSAGE_EFFECTIVE_ERROR);
      }
    }
    int i = generatePhoneCaptcha();
    ZhenziSmsClient client = smsUtil.zhenziSmsClient();
    Map<String, Object> params = new HashMap<>();
    params.put("number", command.getPhone());
    params.put("templateId", SMS_TEMPLATE_CODE);
    String[] templateParams = new String[1];
    templateParams[0] = String.valueOf(i);
    params.put("templateParams", templateParams);
    String result = null;
    try {
      result = client.send(params);
    } catch (Exception e) {
      log.info(e.getMessage());
//      throw new UserAuthenticationException(AuthenticationError.SMS_MESSAGE_ERROR);
    }
//    JSONObject parse = (JSONObject) JSONObject.parse(result);
//    if(!parse.get("code").equals("0")){
//      String data = (String) parse.get("data");
//      log.error(data);
//      throw new UserAuthenticationException(AuthenticationError.SMS_MESSAGE_ERROR,data);
//    }
    log.info("SMS短信服务" + result);
    PhoneMessageCaptchaDTO dto = PhoneMessageCaptchaDTO.builder()
        .phone(command.getPhone())
        .startTime(LocalDateTime.now())
        .durationTime(phoneCaptchaTime * 60)
        .code(String.valueOf(i))
        .build();
    redisTemplate.opsForValue().set(LOGIN_CAPTCHA_SMS_PREFIX + s,dto,1, TimeUnit.DAYS);
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = RuntimeException.class)
  public Result<String> forgetPassword(ForgetPasswordCommand command, HttpServletRequest  request)
      throws Exception {
    CaptchaDTO captcha = (CaptchaDTO) request.getSession().getAttribute(SESSION_CAPTCHA_NAME);
    if(ObjectUtil.isNull(captcha)){
      throw new UserAuthenticationException(AuthenticationError.CAPTCHA_GET_ERROR);
    }
    if(captcha.getStartTime().plusMinutes(captchaTime).isBefore(LocalDateTime.now())){
      throw new UserAuthenticationException(AuthenticationError.CAPTCHA_TIME_ERROR);
    }
    if(!command.getCaptcha().equals(captcha.getText())){
      throw new UserAuthenticationException(AuthenticationError.CAPTCHA_DIFFERENT_ERROR);
    }
    request.getSession().removeAttribute(SESSION_CAPTCHA_NAME);
    String account = command.getAccount();
    User user;
    if(Validator.isMobile(account)){
      //手机登陆
      List<User> userList = getBaseMapper().selectList(
          new LambdaQueryWrapper<User>().eq(User::getPhone, account));
      if(userList.size() > 1){
        throw new UserAuthenticationException(AuthenticationError.LOGIN_ASSOCIATED_PHONE_MORE_ERROR);
      }
      user = userList.get(0);
    }else{
      user = getBaseMapper().selectOne(new LambdaQueryWrapper<User>().eq(User::getAccount,account));
    }
    if(ObjectUtil.isNull(user)){
      throw new UserAuthenticationException(AuthenticationError.LOGIN_ACCOUNT_NOT_EXIST_ERROR);
    }
    //随机生成动态密码
    String newPwd = HexUtil.encodeHexStr(System.currentTimeMillis() + account,
        StandardCharsets.UTF_8).substring(0,7);
    String newEncryptionPwd = MD5.SysMd5(user.getAccount(), newPwd);
    user.setPassword(newEncryptionPwd);
    userMapper.updateById(user);
    ZhenziSmsClient client = smsUtil.zhenziSmsClient();
    Map<String, Object> params = new HashMap<>();
    params.put("number", user.getPhone());
    params.put("templateId", SMS_TEMPLATE_FORGET_CODE);
    String[] templateParams = new String[1];
    templateParams[0] = newPwd;
    params.put("templateParams", templateParams);
    String result = null;
    //第三方插件异常不能try，会导致事务失效
    result = client.send(params);
    return ResultFactory.successResult(result);
  }

  @Override
  public Result<Boolean> getCaptchaStatue(String account,HttpServletRequest request) {
    if(captchaSwitch){
      if(captchaAuto){
        if(ObjectUtil.isNull(account)){
          return ResultFactory.successResult(false);
        }
        Object attributeObject = request.getSession().getAttribute(SESSION_CAPTCHA_AUTO_ID + account);
        if(ObjectUtil.isNull(attributeObject)){
          return ResultFactory.successResult(false);
        }
        if((Integer) attributeObject > captchaAutoNumber){
          return ResultFactory.successResult(true);
        }
      }else {
        return ResultFactory.successResult(true);
      }
    }
    return ResultFactory.successResult(false);
  }

  private void loginByPassword(LoginCommand command,HttpServletRequest request){
    User user = shiroService.findByUsername(command.getAccount());
    if(ObjectUtil.isNull(user)){
      addAutoNumber(command.getAccount(),request);
      throw new UserAuthenticationException(AuthenticationError.LOGIN_ACCOUNT_NOT_EXIST_ERROR);
    }
    String password = MD5.SysMd5(String.valueOf(user.getAccount()),command.getPassword());
    if(!user.getPassword().equals(password)){
      addAutoNumber(command.getAccount(),request);
      throw new UserAuthenticationException(AuthenticationError.LOGIN_PASSWORD_ERROR);
    }
  }

  private void loginByPhone(LoginCommand command) {
    if(smsCaptchaSwitch.equals(Boolean.FALSE)){
      throw new UserAuthenticationException(AuthenticationError.SMS_SWITCH_CLOSE_ERROR);
    }
    String s = MD5.SysMd5(LOGIN_CAPTCHA_SMS_PREFIX, command.getAccount());
    PhoneMessageCaptchaDTO o = (PhoneMessageCaptchaDTO) redisTemplate.opsForValue().get(
        LOGIN_CAPTCHA_SMS_PREFIX + s);
    if(ObjectUtil.isNull(o)){
      throw new UserAuthenticationException(AuthenticationError.SMS_MESSAGE_NULL_ERROR);
    }
    if(!o.getCode().equals(command.getPassword())){
      throw new UserAuthenticationException(AuthenticationError.SMS_CODE_ERROR);
    }
    if(o.getStartTime().plusSeconds(o.getDurationTime()).isBefore(LocalDateTime.now())){
      throw new UserAuthenticationException(AuthenticationError.SMS_TIME_LONG_ERROR);
    }
    List<User> userList = getBaseMapper().selectList(
        new LambdaQueryWrapper<User>().eq(User::getPhone, command.getAccount()));
    if(userList.isEmpty()){
      throw new UserAuthenticationException(AuthenticationError.LOGIN_ACCOUNT_NOT_EXIST_ERROR);
    }
    if(userList.size() > 1){
      throw new UserAuthenticationException(AuthenticationError.LOGIN_ASSOCIATED_PHONE_MORE_ERROR);
    }
    command.setAccount(userList.get(0).getAccount());
  }

  /**
   * 填充用户基本信息
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

  /**
   * 生成六位手机验证码
   */
  protected static int generatePhoneCaptcha(){
    return RandomUtil.randomInt(100000, 999999);
  }

  /**
   * 验证码前置条件筛选
   */
  private void captchaPrepositionValidate(LoginCommand command, LoginTypeEnums type,HttpServletRequest request){
    if(captchaSwitch.equals(Boolean.TRUE) && type.equals(LoginTypeEnums.PC_PASSWORD)){
      if(captchaAuto){
        Integer attribute = (Integer) request.getSession()
            .getAttribute(SESSION_CAPTCHA_AUTO_ID + command.getAccount());
        if(ObjectUtil.isNotNull(attribute) && attribute > captchaAutoNumber) {
          captchaValidate(command,type,request);
        }
      }else {
        captchaValidate(command,type,request);
      }
    }
  }

  /**
   * 验证码筛选
   */
  private void captchaValidate(LoginCommand command, LoginTypeEnums type,HttpServletRequest request){
    CaptchaDTO captcha = (CaptchaDTO) request.getSession().getAttribute(SESSION_CAPTCHA_NAME);
    if(ObjectUtil.isNull(captcha)){
      throw new UserAuthenticationException(AuthenticationError.CAPTCHA_GET_ERROR);
    }
    if(captcha.getStartTime().plusMinutes(captchaTime).isBefore(LocalDateTime.now())){
      throw new UserAuthenticationException(AuthenticationError.CAPTCHA_TIME_ERROR);
    }
    if(!command.getCaptcha().equals(captcha.getText())){
      throw new UserAuthenticationException(AuthenticationError.CAPTCHA_DIFFERENT_ERROR);
    }
    request.getSession().removeAttribute(SESSION_CAPTCHA_NAME);
  }

  /**
   * 增加自适应验证次数
   */
  private void addAutoNumber(String account,HttpServletRequest request){
    if(captchaAuto){
      Object attribute = request.getSession().getAttribute(SESSION_CAPTCHA_AUTO_ID + account);
      if(ObjectUtil.isNull(attribute)){
        request.getSession().setAttribute(SESSION_CAPTCHA_AUTO_ID + account,1);
      }else {
        Integer num = (Integer) attribute;
        request.getSession().setAttribute(SESSION_CAPTCHA_AUTO_ID + account, ++num);
      }
    }
  }
}
