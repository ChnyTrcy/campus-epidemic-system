package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.app.analysis
 * @ClassName: CountPeopleDistributionVO
 * @Author: ChnyTrcy
 * @Description: 统计人数分布VO
 * @Date: 2022/10/20 09:03
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("统计人数分布VO")
public class CountPeopleDistributionVO extends Base {

  @ApiModelProperty("总隔离人数")
  private Integer allIsolationNum;

  @ApiModelProperty("总隔离人数百分比")
  private Integer allIsolationPercent;

  @ApiModelProperty("总隔离人数是否正增长")
  private Boolean allIsolationPositive;

  @ApiModelProperty("新增隔离人数")
  private Integer newIsolationNum;

  @ApiModelProperty("新增隔离人数百分比")
  private Integer newIsolationPercent;

  @ApiModelProperty("新增隔离人数是否正增长")
  private Boolean newIsolationPositive;

  @ApiModelProperty("新增解除隔离人数")
  private Integer newRemoveNum;

  @ApiModelProperty("新增解除隔离人数百分比")
  private Integer newRemovePercent;

  @ApiModelProperty("新增解除隔离人数是否正增长")
  private Boolean newRemovePositive;

  @ApiModelProperty("昨日请假人数")
  private Integer leaveYesterdayNum;

  @ApiModelProperty("昨日请假人数百分比")
  private Integer leaveYesterdayPercent;

  @ApiModelProperty("昨日请假人数是否正增长")
  private Boolean leaveYesterdayPositive;
}
