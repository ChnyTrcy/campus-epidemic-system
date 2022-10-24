package chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance.RejectEpidemicFeedbackAcceptanceCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance
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
