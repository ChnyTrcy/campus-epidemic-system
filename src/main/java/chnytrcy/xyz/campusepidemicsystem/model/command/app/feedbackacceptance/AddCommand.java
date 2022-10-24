package chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance
 * @ClassName: AddEpidemicFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 添加防疫人员的反馈Command
 * @Date: 2022/9/13 9:53 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("添加防疫人员的反馈Command")
public class AddCommand extends Base {

  @ApiModelProperty(value = "反馈类型(1:物资请求，2:意见申诉)",required = true)
  @NotNull(message = "反馈类型不能为空")
  @ListValue(vals = {1,2},message = "反馈类型错误")
  private Integer type;

  @ApiModelProperty(value = "反馈内容",required = true)
  @NotBlank(message = "反馈内容不能为空")
  private String message;

}
