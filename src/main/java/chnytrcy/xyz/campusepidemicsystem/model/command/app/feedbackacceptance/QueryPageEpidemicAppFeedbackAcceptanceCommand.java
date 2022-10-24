package chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceCommand;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance
 * @ClassName: QueryPageEpidemicAppFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 防疫人员查看与自己相关的反馈受理Command
 * @Date: 2022/9/13 10:04 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员查看与自己相关的反馈受理Command")
public class QueryPageEpidemicAppFeedbackAcceptanceCommand extends
    QueryPageEpidemicFeedbackAcceptanceCommand {


}
