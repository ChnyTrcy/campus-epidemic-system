package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance
 * @ClassName: AnalyzeAdminFeedbackAcceptanceVO
 * @Author: ChnyTrcy
 * @Description: 统计分析管理员对应的反馈受理VO
 * @Date: 2022/9/17 18:14
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("统计分析管理员对应的反馈受理VO")
public class AnalyzeAdminFeedbackAcceptanceVO extends Base {

  @ApiModelProperty("未处理数量")
  private Integer undoneCount;

  @ApiModelProperty("同意数量")
  private Integer solvedCount;

  @ApiModelProperty("总数")
  private Integer allCount;

}
