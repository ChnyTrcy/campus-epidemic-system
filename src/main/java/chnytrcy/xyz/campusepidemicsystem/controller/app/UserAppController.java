package chnytrcy.xyz.campusepidemicsystem.controller.app;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.ChangePwdCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.LoginCommand;
import chnytrcy.xyz.campusepidemicsystem.model.constance.LoginMethodConstance;
import chnytrcy.xyz.campusepidemicsystem.service.pc.UserService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.app
 * @ClassName: UserAppController
 * @Author: ChnyTrcy
 * @Description: 移动端用户Controller
 * @Date: 2022/9/1 1:47 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/user")
@Api(value = "移动端 - 用户Controller",tags = "App - 用户接口")
public class UserAppController {

  @Autowired
  private UserService userService;

  @ApiOperation("登陆")
  @PostMapping("/login")
  public Result login(@RequestBody @Valid LoginCommand command, HttpServletRequest request){
    return userService.login(command, LoginMethodConstance.MOBILE,request);
  }

  @ApiOperation("登出")
  @GetMapping("/logout")
  public Result logout(){
    return userService.loginOut();
  }

  @ApiOperation("修改密码")
  @PostMapping("/changePwd")
  public Result changePwd(@Valid @RequestBody ChangePwdCommand command){
    return userService.changePwd(command);
  }
}
