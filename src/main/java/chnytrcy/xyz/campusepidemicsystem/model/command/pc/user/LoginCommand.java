package chnytrcy.xyz.campusepidemicsystem.model.command.pc.user;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName LoginCommand
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/21 2:03 PM
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LoginCommand extends Base {

  @ApiModelProperty(value = "用户名",required = true)
  @NotBlank(message = "用户名不能为空")
  private String account;

  @ApiModelProperty(value = "密码",required = true)
  @NotBlank(message = "密码不能为空")
  private String password;

  @ApiModelProperty(value = "验证码")
  private String captcha = "";
}
