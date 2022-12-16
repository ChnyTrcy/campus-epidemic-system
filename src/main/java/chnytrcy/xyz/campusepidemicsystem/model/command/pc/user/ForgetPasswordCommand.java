package chnytrcy.xyz.campusepidemicsystem.model.command.pc.user;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.user
 * @ClassName: ForgetPasswordCommand
 * @Author: ChnyTrcy
 * @Description: 忘记密码Command
 * @Date: 2022/12/16 16:19
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("忘记密码Command")
public class ForgetPasswordCommand extends Base {

  @ApiModelProperty("账户名/手机号")
  @NotBlank(message = "账户名/手机号不能为空")
  private String account;

  @ApiModelProperty("验证码")
  @NotBlank(message = "验证码不能为空")
  private String captcha;

}
