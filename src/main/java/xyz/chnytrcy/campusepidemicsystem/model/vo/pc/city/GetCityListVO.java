package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.city
 * @ClassName: GetCityListVO
 * @Author: ChnyTrcy
 * @Description: 获得城市列表VO
 * @Date: 2022/8/28 5:42 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("获得城市列表VO")
public class GetCityListVO extends Base {

  @ApiModelProperty("城市编号")
  private String code;

  @ApiModelProperty("城市名称")
  private String name;

}
