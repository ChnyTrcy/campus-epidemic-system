package xyz.chnytrcy.campusepidemicsystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.entity
 * @ClassName: FeedbackAcceptance
 * @Author: ChnyTrcy
 * @Description: 反馈受理实体类
 * @Date: 2022/8/24 3:30 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("feedback_acceptance")
@ApiModel("反馈受理实体类")
public class FeedbackAcceptance extends BaseEntity {

  @ApiModelProperty(value = "发起人（工号/学号）",required = true)
  private String producerCode;

  @ApiModelProperty(value = "发起人名称",required = true)
  private String producerName;

  @ApiModelProperty(value = "发起人类型（1:隔离人员，2:防疫人员）",required = true)
  private Integer producerType;

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

  @ApiModelProperty(value = "是否结束",required = true,example = "0")
  private Integer isEnd;

  @ApiModelProperty(value = "是否与管理员相关",required = true,example = "0")
  private Integer adminRelatedTag;
}
