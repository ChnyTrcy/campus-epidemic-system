package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.province;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.province
 * @ClassName: GetProvinceListVO
 * @Author: ChnyTrcy
 * @Description: 获得省份列表VO
 * @Date: 2022/8/28 5:32 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获得省份列表VO")
@EqualsAndHashCode(callSuper = true)
public class GetProvinceListVO extends Base {

  @ApiModelProperty("省份编号")
  private String code;

  @ApiModelProperty("省份名称")
  private String name;
}
