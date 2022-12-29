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
 * @ClassName: AgreeLeaveCommand
 * @Author: ChnyTrcy
 * @Description: 同意请假Command
 * @Date: 2022/8/30 4:52 PM
 * @Version: 1.0
 */
@ApiModel("同意请假Command")
@Data
@EqualsAndHashCode(callSuper = true)
public class AgreeLeaveCommand extends BaseId {

  @ApiModelProperty("回信")
  @NotBlank(message = "回复内容不能为空")
  private String message;

}
