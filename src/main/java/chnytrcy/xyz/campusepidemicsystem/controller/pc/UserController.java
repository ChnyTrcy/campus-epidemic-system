package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.RateLimitAnnotation;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.AddUserCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.ChangePwdCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.LoginByPhoneCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.LoginCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.user.PhoneMessageCaptchaCommand;
import chnytrcy.xyz.campusepidemicsystem.model.constance.LoginMethodConstance;
import chnytrcy.xyz.campusepidemicsystem.model.dto.CaptchaDTO;
import chnytrcy.xyz.campusepidemicsystem.model.enums.LoginTypeEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.UserService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

  @Autowired private UserService userService;

  @Autowired private DefaultKaptcha defaultKaptcha;


  @ApiOperation("注册")
  @PostMapping("/addUser")
  public Result<Void> addUser(@RequestBody @Valid AddUserCommand command){
    return userService.addUser(command);
  }

  @ApiOperation("账号密码登陆")
  @PostMapping("/loginByPassword")
  public Result login(@RequestBody @Valid LoginCommand command,HttpServletRequest request){
    return userService.login(command, LoginTypeEnums.PC_PASSWORD,request);
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

  @ApiOperation("获取验证码")
  @PostMapping("/getCaptchaImg")
  public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setDateHeader("Expires",0);
    response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
    response.addHeader("Cache-Control","post-check=0, pre-check=0");
    response.setHeader("pragma","no-cache");
//    response.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
    response.setContentType("image/jpg");
    String text = defaultKaptcha.createText();
    request.getSession().setAttribute("captcha",new CaptchaDTO(text, LocalDateTime.now()));
    BufferedImage image = defaultKaptcha.createImage(text);
    ServletOutputStream outputStream = null;
    outputStream = response.getOutputStream();
    //输出流输出图片,格式为jpg
    ImageIO.write(image,"jpg",outputStream);
    outputStream.flush();
    if (outputStream != null){
      try {
        outputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @ApiOperation("发送短信验证码")
  @PostMapping("/getPhoneMessageCaptcha")
//  @RateLimitAnnotation(value = 1)
  public Result<Void> getPhoneMessageCaptcha(@Valid @RequestBody PhoneMessageCaptchaCommand command){
    return userService.getPhoneMessageCaptcha(command);
  }

  @ApiOperation("手机登陆")
  @PostMapping("/loginByPhone")
  public Result loginByPhone(@Valid @RequestBody LoginCommand command,HttpServletRequest request){
    return userService.login(command,LoginTypeEnums.PC_PHONE,request);
  }

}
