package xyz.chnytrcy.campusepidemicsystem.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;
import xyz.chnytrcy.core.config.basic.entity.EnumTransformation;
import xyz.chnytrcy.core.config.basic.entity.EnumValue;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.entity
 * @ClassName: Street
 * @Author: ChnyTrcy
 * @Description: 街道实体类
 * @Date: 2022/8/24 4:51 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("街道实体类")
@EnumTransformation
public class Street extends BaseEntity {

  @ApiModelProperty(value = "街道编号",required = true)
  private String code;

  @ApiModelProperty(value = "街道名称",required = true)
  private String name;

  @ApiModelProperty(value = "父级省份编号",required = true)
  private String provinceCode;

  @ApiModelProperty(value = "父级城市编号",required = true)
  private String cityCode;

  @ApiModelProperty(value = "父级区县编号",required = true)
  private String countyCode;

  @ApiModelProperty(value = "全称",required = true)
  private String fullName;

  @ApiModelProperty(value = "风险等级",required = true,example = "0")
  @EnumValue("RISK_LEVEL")
  private Integer riskLevel;

}
