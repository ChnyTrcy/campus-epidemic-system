package xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave
 * @ClassName: DealReturnLeaveCommand
 * @Author: ChnyTrcy
 * @Description: 管理员回复请假申请Command
 * @Date: 2022/9/7 11:00 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("管理员回复请假申请Command")
public class DealReturnLeaveCommand extends BaseId {

  @ApiModelProperty(value = "回复信息",required = true)
  @NotBlank(message = "回复信息不能为空")
  private String message;
}
