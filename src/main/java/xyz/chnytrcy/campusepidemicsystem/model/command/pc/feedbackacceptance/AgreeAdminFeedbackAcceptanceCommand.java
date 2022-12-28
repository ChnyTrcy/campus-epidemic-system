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
 * @ClassName: AgreeAdminFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 同意反馈Command
 * @Date: 2022/8/30 2:40 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("同意反馈Command")
public class AgreeAdminFeedbackAcceptanceCommand extends BaseId {

  @ApiModelProperty(value = "反馈回信",required = true)
  @NotBlank(message = "反馈回信不能为空")
  private String messageReturn;

}
