package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.county;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.county
 * @ClassName: GetCountyListVo
 * @Author: ChnyTrcy
 * @Description: 获得区县列表VO
 * @Date: 2022/8/28 5:53 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("获得区县列表VO")
public class GetCountyListVO extends Base {

  @ApiModelProperty("区县编号")
  private String code;

  @ApiModelProperty("区县名称")
  private String name;

}
