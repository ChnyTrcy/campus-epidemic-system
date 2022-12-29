package xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.RejectEpidemicFeedbackAcceptanceCommand;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance
 * @ClassName: RejectCommand
 * @Author: ChnyTrcy
 * @Description: 拒绝反馈受理Command
 * @Date: 2022/9/13 9:38 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("拒绝反馈受理Command")
public class RejectCommand extends RejectEpidemicFeedbackAcceptanceCommand {

}
