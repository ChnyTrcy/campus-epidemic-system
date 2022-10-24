package chnytrcy.xyz.campusepidemicsystem.model.vo.app.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.app.feedbackacceptance
 * @ClassName: AnalyzeEpidemicFeedbackAcceptanceVO
 * @Author: ChnyTrcy
 * @Description: 防疫人员分析与自己相关的反馈受理VO
 * @Date: 2022/10/7 16:41
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员分析与自己相关的反馈受理VO")
public class AnalyzeEpidemicFeedbackAcceptanceVO extends Base {

  @ApiModelProperty("未处理数量")
  private Integer undoneCount;

  @ApiModelProperty("同意数量")
  private Integer solvedCount;

  @ApiModelProperty("总数")
  private Integer allCount;

}
