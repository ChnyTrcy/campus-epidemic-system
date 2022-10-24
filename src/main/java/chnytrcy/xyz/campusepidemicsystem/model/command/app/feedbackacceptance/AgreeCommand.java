package chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance.AgreeEpidemicFeedbackAcceptanceCommand;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance
 * @ClassName: AgreeCommand
 * @Author: ChnyTrcy
 * @Description: 同意反馈受理Command
 * @Date: 2022/9/13 9:23 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("同意反馈受理Command")
public class AgreeCommand extends AgreeEpidemicFeedbackAcceptanceCommand {

}
