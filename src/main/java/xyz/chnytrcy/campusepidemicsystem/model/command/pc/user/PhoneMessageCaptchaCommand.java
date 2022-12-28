package xyz.chnytrcy.campusepidemicsystem.model.command.pc.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.Phone;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.pc.user
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
