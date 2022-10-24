package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.AddUserCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.ChangePwdCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.LoginCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service
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
   * @param command 入参
   * @return 结果
   */
  Result login(LoginCommand command,Integer i);

  /**
   * 登出
   */
  Result loginOut();

  /**
   * 修改密码
   */
  Result changePwd(ChangePwdCommand command);
}
