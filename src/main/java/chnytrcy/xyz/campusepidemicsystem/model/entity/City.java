package chnytrcy.xyz.campusepidemicsystem.model.entity;

import chnytrcy.xyz.campusepidemicsystem.config.basic.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.entity
 * @ClassName: City
 * @Author: ChnyTrcy
 * @Description: 城市实体类
 * @Date: 2022/8/24 3:18 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("城市实体类")
public class City extends BaseEntity {

  @ApiModelProperty(value = "城市名",required = true)
  private String name;

  @ApiModelProperty(value = "城市编号",required = true)
  private String code;

  @ApiModelProperty(value = "父级省份编号",required = true)
  @TableField("province_code")
  private String provinceCode;

  @ApiModelProperty(value = "全称",required = true)
  @TableField("full_name")
  private String fullName;

}
