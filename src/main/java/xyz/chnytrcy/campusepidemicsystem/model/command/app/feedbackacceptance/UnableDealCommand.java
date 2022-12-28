package xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance;

import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.UnableDealEpidemicFeedbackAcceptanceCommand;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance
 * @ClassName: UnableDealCommand
 * @Author: ChnyTrcy
 * @Description: 无法处理Command
 * @Date: 2022/9/13 9:48 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("无法处理Command")
public class UnableDealCommand extends UnableDealEpidemicFeedbackAcceptanceCommand {

}
