package xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance
 * @ClassName: AgreeEpidemicFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 防疫人员同意该条受理Command
 * @Date: 2022/9/5 8:19 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员同意该条受理Command")
public class AgreeEpidemicFeedbackAcceptanceCommand extends BaseId {

  @ApiModelProperty("处理回信")
  private String returnMessage;

}
