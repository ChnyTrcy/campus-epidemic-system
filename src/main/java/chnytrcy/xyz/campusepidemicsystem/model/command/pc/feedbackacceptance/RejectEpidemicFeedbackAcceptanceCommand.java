package chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance
 * @ClassName: RejectEpidemicFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 防疫人员拒绝该条受理Command
 * @Date: 2022/9/5 8:26 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员拒绝该条受理Command")
public class RejectEpidemicFeedbackAcceptanceCommand extends BaseId {

  @ApiModelProperty("处理回信")
  private String returnMessage;
}
