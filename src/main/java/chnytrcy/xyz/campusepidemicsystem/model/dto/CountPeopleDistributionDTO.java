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
 * @ClassName: CountPeopleDistributionDTO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/10/20 09:25
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("统计分析人数DTO")
public class CountPeopleDistributionDTO extends Base {

  @ApiModelProperty("今天数量")
  private Long now;

  @ApiModelProperty("昨天数量")
  private Long yesterday;

  @ApiModelProperty("前天数量")
  private Long beforeYesterday;

  public CountPeopleDistributionDTO(Long now, Long yesterday) {
    this.now = now;
    this.yesterday = yesterday;
  }
}
