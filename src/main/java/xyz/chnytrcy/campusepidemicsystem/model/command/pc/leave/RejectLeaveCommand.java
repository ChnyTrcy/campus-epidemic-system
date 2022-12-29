package xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.leave
 * @ClassName: RejectLeaveCommand
 * @Author: ChnyTrcy
 * @Description: 拒绝请假Command
 * @Date: 2022/8/30 5:07 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("拒绝请假Command")
public class RejectLeaveCommand extends BaseId {

  @ApiModelProperty("回信")
  @NotBlank(message = "回复内容不能为空")
  private String message;

}
