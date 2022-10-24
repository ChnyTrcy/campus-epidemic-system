package chnytrcy.xyz.campusepidemicsystem.model.dto;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.dto
 * @ClassName: StatisticalNameCountDTO
 * @Author: ChnyTrcy
 * @Description: 统计分析名称数量DTO
 * @Date: 2022/10/20 15:56
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("统计分析名称数量DTO")
public class StatisticalNameCountDTO extends Base {

  @ApiModelProperty("名称")
  private String name;

  @ApiModelProperty("数量")
  private Integer count;

}
