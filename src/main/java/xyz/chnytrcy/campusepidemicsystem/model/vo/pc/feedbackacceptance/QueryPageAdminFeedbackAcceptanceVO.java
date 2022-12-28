package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.feedbackacceptance
 * @ClassName: QueryPageAdminFeedbackAcceptanceVO
 * @Author: ChnyTrcy
 * @Description: 分页查询反馈受理VO
 * @Date: 2022/8/30 2:25 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询反馈受理VO")
public class QueryPageAdminFeedbackAcceptanceVO extends BaseId {

  @ApiModelProperty(value = "发起人（工号/学号）")
  private String producerCode;

  @ApiModelProperty(value = "发起人名称")
  private String producerName;

  @ApiModelProperty(value = "发起人类型（1:隔离人员，2:防疫人员）")
  private Integer producerType;

  @ApiModelProperty(value = "反馈类型(1:物资请求，2:意见申诉)")
  private Integer type;

  @ApiModelProperty(value = "反馈内容")
  private String message;

  @ApiModelProperty(value = "处理回信")
  private String messageReturn;

  @ApiModelProperty(value = "处理结果（0:未读，1:同意，2:拒绝，3:无法处理）")
  private Integer result;

}
