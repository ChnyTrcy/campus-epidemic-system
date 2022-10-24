package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave
 * @ClassName: AnalyzeAdminLeaveVO
 * @Author: ChnyTrcy
 * @Description: 统计分析请假数VO
 * @Date: 2022/9/17 18:27
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("统计分析请假数VO")
public class AnalyzeAdminLeaveVO extends Base {

  @ApiModelProperty("未处理")
  private Integer undoneCount;

  @ApiModelProperty("同意个数")
  private Integer solvedCount;

  @ApiModelProperty("总数")
  private Integer allCount;
}
