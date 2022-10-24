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
 * @ClassName: ChangePwdCommand
 * @Author: ChnyTrcy
 * @Description: 修改密码Command
 * @Date: 2022/9/6 8:47 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改密码Command")
public class ChangePwdCommand extends Base {

  @ApiModelProperty(value = "密码",required = true)
  @NotBlank(message = "密码不能为空")
  private String password;

  @ApiModelProperty(value = "重复密码",required = true)
  @NotBlank(message = "重复密码不能为空")
  private String passwordRepeat;
}
