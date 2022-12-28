package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.street;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.pc.street
 * @ClassName: QueryStatisticsStreetVO
 * @Author: ChnyTrcy
 * @Description: 统计风险地区VO
 * @Date: 2022/9/18 01:36
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("统计风险地区VO")
public class QueryStatisticsStreetVO extends Base {

  @ApiModelProperty("高风险")
  private Integer highNum;

  @ApiModelProperty("中风险")
  private Integer midNum;

  @ApiModelProperty("低风险")
  private Integer normalNum;

}
