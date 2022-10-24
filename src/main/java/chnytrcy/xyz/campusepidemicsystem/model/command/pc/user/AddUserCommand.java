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
 * @ClassName AddUserCommand
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/21 11:14 AM
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AddUserCommand extends Base {

  @NotBlank(message = "用户名不能为空")
  @ApiModelProperty(value = "用户名",required = true)
  private String account;

  @NotBlank(message = "密码不能为空")
  @ApiModelProperty(value = "密码",required = true)
  private String password;
}
