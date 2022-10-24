package chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance
 * @ClassName: UnableDealEpidemicFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 防疫人员无法受理该条受理Command
 * @Date: 2022/9/5 8:30 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员无法受理该条受理Command")
public class UnableDealEpidemicFeedbackAcceptanceCommand extends BaseId {

  @ApiModelProperty("处理回信")
  private String returnMessage;

}
