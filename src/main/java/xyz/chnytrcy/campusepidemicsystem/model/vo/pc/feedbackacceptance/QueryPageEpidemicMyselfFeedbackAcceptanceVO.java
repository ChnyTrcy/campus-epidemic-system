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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance
 * @ClassName: QueryPageEpidemicMyselfFeedbackAcceptanceVO
 * @Author: ChnyTrcy
 * @Description: 防疫人员查看自己提出的反馈受理VO
 * @Date: 2022/9/5 9:42 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员查看自己提出的反馈受理VO")
public class QueryPageEpidemicMyselfFeedbackAcceptanceVO extends BaseId {

  @ApiModelProperty(value = "负责人编号",required = true)
  private Long adminCode;

  @ApiModelProperty(value = "反馈类型(1:物资请求，2:意见申诉)",required = true)
  private Integer type;

  @ApiModelProperty(value = "反馈内容",required = true)
  private String message;

  @ApiModelProperty(value = "处理回信")
  private String messageReturn;

  @ApiModelProperty(value = "处理结果（0:未读，1:同意，2:拒绝，3:无法处理）",required = true)
  private Integer result;

}
