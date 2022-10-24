package chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationperson;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationperson
 * @ClassName: ComplexFeedbackAcceptanceVO
 * @Author: ChnyTrcy
 * @Description: 嵌套VO
 * @Date: 2022/9/12 1:46 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("嵌套VO")
public class ComplexFeedbackAcceptanceVO extends BaseId {

  @ApiModelProperty(value = "反馈类型(1:物资请求，2:意见申诉)")
  private Integer type;

  @ApiModelProperty(value = "反馈内容")
  private String message;

  @ApiModelProperty(value = "处理回信")
  private String messageReturn;

  @ApiModelProperty(value = "处理结果（0:未读，1:同意，2:拒绝，3:无法处理）")
  private Integer result;

  @ApiModelProperty(value = "是否结束")
  private Integer isEnd;

}
