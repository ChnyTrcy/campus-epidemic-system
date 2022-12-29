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
 * @ClassName: QueryPageEpidemicFeedbackAcceptanceVO
 * @Author: ChnyTrcy
 * @Description: 查看防疫人员的反馈受理VO
 * @Date: 2022/9/4 9:59 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查看防疫人员的反馈受理VO")
public class QueryPageEpidemicFeedbackAcceptanceVO extends BaseId {

  @ApiModelProperty(value = "发起人学生学号(由学生发起的)")
  private String producerCode;

  @ApiModelProperty(value = "发起人学生姓名（由学生发起的）")
  private String producerName;

  @ApiModelProperty(value = "处理结果（0:未读，1:同意，2:拒绝，3:无法处理）")
  private Integer result;

  @ApiModelProperty(value = "反馈内容",required = true)
  private String message;

  @ApiModelProperty(value = "处理回信")
  private String messageReturn;

  @ApiModelProperty(value = "类型（1：由学生发起的，2：由自己发起的）")
  private Integer feedbackAcceptanceType;

  @ApiModelProperty(value = "反馈类型(1:物资请求，2:意见申诉)")
  private Integer type;

  @ApiModelProperty(value = "管理员编号")
  private String adminCode;
}
