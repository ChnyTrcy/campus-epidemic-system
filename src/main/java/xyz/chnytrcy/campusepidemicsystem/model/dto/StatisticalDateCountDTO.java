package xyz.chnytrcy.campusepidemicsystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.dto
 * @ClassName: StatisticalNameCountDTO
 * @Author: ChnyTrcy
 * @Description: 统计分析日期数量DTO
 * @Date: 2022/10/20 15:56
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("统计分析日期数量DTO")
public class StatisticalDateCountDTO extends Base {

  @ApiModelProperty("日期")
  private String time;

  @ApiModelProperty("数量")
  private Integer count;

}
