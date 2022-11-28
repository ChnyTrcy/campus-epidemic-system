package chnytrcy.xyz.campusepidemicsystem.model.command.pc.user;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.Phone;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.user
 * @ClassName: LoginByPhoneCommand
 * @Author: ChnyTrcy
 * @Description: 手机登陆Command
 * @Date: 2022/11/22 17:25
 * @Version: 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改密码Command")
public class LoginByPhoneCommand extends Base {

  @ApiModelProperty("手机号")
  @Phone
  @NotBlank(message = "手机号不能为空")
  private String phone;

  @ApiModelProperty("短信验证码")
  @NotBlank(message = "短信验证码不能为空")
  @Size(min = 6,max = 6,message = "短信验证码长度错误")
  private String captchaCode;

}
