package chnytrcy.xyz.campusepidemicsystem.model.command.pc.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.feedbackAcceptance
 * @ClassName: QueryPageAdminFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 分页查询反馈受理Command
 * @Date: 2022/8/30 11:17 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询反馈受理Command")
public class QueryPageAdminFeedbackAcceptanceCommand extends BasePageCommand {

  @ApiModelProperty(value = "查看类型（1：隔离人员，2：防疫人员，3：查看所有）",required = true)
  @NotNull(message = "查看类型不能为空")
  @ListValue(vals = {1,2,3},message = "查看类型错误")
  private Integer queryType;

  @ApiModelProperty(value = "反馈类型（0:默认,1:物资请求，2:意见申诉）",required = true)
  @NotNull(message = "反馈类型不能为空")
  @ListValue(vals = {0,1,2},message = "反馈类型错误")
  private Integer feedbackType;

}
