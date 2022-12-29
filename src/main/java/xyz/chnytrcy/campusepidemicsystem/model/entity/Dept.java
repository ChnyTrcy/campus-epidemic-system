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
 * @ClassName: Dept
 * @Author: ChnyTrcy
 * @Description: 院系实体类
 * @Date: 2022/8/24 8:21 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("院系实体类")
public class Dept extends BaseEntity {

  @ApiModelProperty(value = "院系编号",required = true)
  private String code;

  @ApiModelProperty(value = "院系名称",required = true)
  private String name;

}
