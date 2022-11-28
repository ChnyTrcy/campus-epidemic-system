package chnytrcy.xyz.campusepidemicsystem.model.command.pc.user;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.Phone;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.user
 * @ClassName: PhoneMessageCaptchaCommand
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/21 16:05
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改密码Command")
public class PhoneMessageCaptchaCommand extends Base {

  @ApiModelProperty("手机号码")
  @Phone
  private String phone;

}
