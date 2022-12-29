package xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance
 * @ClassName: QueryEpidemicFeedbackAcceptanceCommand
 * @Author: ChnyTrcy
 * @Description: 防疫人员查看与自己相关的反馈受理Command
 * @Date: 2022/9/4 3:51 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员查看与自己相关的反馈受理Command")
public class QueryPageEpidemicFeedbackAcceptanceCommand extends BasePageCommand {

  @ApiModelProperty(value = "反馈类型（0:默认,1:物资请求，2:意见申诉）",required = true)
  @NotNull(message = "反馈类型不能为空")
  @ListValue(vals = {0,1,2},message = "反馈类型错误")
  private Integer feedbackType;

  @ApiModelProperty(value = "是否为自己发起的（0：默认全看，1：只看自己发起的，2：只看自己处理的）",required = true)
  @NotNull(message = "是否自己发起字段不能为空")
  @ListValue(vals = {0,1,2},message = "是否自己发起字段错误")
  private Integer isSponsor;

}
