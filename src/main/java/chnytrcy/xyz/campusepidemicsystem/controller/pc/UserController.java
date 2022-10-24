package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.AddUserCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.ChangePwdCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.LoginCommand;
import chnytrcy.xyz.campusepidemicsystem.model.constance.LoginMethodConstance;
import chnytrcy.xyz.campusepidemicsystem.service.pc.UserService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/21 11:12 AM
 * @Version 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/user")
@Api(value = "用户Controller",tags = "PC - 用户接口")
public class UserController {

  @Autowired
  private UserService userService;


  @ApiOperation("注册")
  @PostMapping("/addUser")
  public Result<Void> addUser(@RequestBody @Valid AddUserCommand command){
    return userService.addUser(command);
  }

  @ApiOperation("登陆")
  @PostMapping("/loginByPassword")
  public Result login(@RequestBody @Valid LoginCommand command){
    return userService.login(command, LoginMethodConstance.PC);
  }

  @ApiOperation("登出")
  @GetMapping("/loginOut")
  public Result loginOut(){
    return userService.loginOut();
  }

  @ApiOperation("修改密码")
  @PostMapping("/changePwd")
  public Result changePwd(@RequestBody @Valid ChangePwdCommand command){
    return userService.changePwd(command);
  }

}
