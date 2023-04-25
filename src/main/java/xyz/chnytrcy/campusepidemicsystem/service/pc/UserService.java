package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import javax.servlet.http.HttpServletRequest;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.AddUserCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.ChangePwdCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.ForgetPasswordCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.LoginCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.user.PhoneMessageCaptchaCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.User;
import xyz.chnytrcy.campusepidemicsystem.model.enums.LoginTypeEnums;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service
 * @InterfaceName: UserService
 * @Author: ChnyTrcy
 * @Description: 用户服务类
 * @Date: 2022/8/23 10:30 PM
 * @Version: 1.0
 */
public interface UserService extends IService<User> {

  /**
   * 添加用户
   * @param command 入参
   * @return 结果
   */
  Result<Void> addUser(AddUserCommand command);

  /**
   * 多端聚合登陆
   */
  Result login(LoginCommand command, LoginTypeEnums type, HttpServletRequest request);

  /**
   * 登出
   */
  Result loginOut();

  /**
   * 修改密码
   */
  Result changePwd(ChangePwdCommand command);

  /**
   * 发送短信验证码
   */
  Result<Void> getPhoneMessageCaptcha(PhoneMessageCaptchaCommand command);

  /**
   * 忘记密码
   */
  Result<String> forgetPassword(ForgetPasswordCommand command, HttpServletRequest request)
      throws Exception;

  /**
   * 获得验证码是否开启状态
   */
  Result<Boolean> getCaptchaStatue(String account,HttpServletRequest request);
}
