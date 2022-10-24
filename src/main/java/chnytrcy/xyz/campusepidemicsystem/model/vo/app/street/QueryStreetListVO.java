package chnytrcy.xyz.campusepidemicsystem.model.vo.app.street;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.app.street
 * @ClassName: QueryStreetListVO
 * @Author: ChnyTrcy
 * @Description: 查询街道列表VO
 * @Date: 2022/9/12 2:15 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询街道列表VO")
public class QueryStreetListVO extends BaseId {

  @ApiModelProperty(value = "街道编号")
  private String code;

  @ApiModelProperty(value = "街道名称")
  private String name;

}
