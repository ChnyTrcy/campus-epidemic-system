package xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.feedbackacceptance
 * @ClassName: RejectAdminFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 拒绝反馈Command
 * @Date: 2022/8/30 3:05 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("拒绝反馈Command")
public class RejectAdminFeedbackAcceptanceCommand extends BaseId {

  @ApiModelProperty(value = "反馈回信",required = true)
  @NotBlank(message = "反馈回信不能为空")
  private String messageReturn;

}
