package xyz.chnytrcy.campusepidemicsystem.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.entity
 * @ClassName: Province
 * @Author: ChnyTrcy
 * @Description: 省份实体类
 * @Date: 2022/8/24 4:48 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("省份实体类")
public class Province extends BaseEntity {

  @ApiModelProperty(value = "省份编号",required = true)
  private String code;

  @ApiModelProperty(value = "省份名称",required = true)
  private String name;
}
