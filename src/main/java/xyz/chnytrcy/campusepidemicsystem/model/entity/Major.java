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
 * @ClassName: Major
 * @Author: ChnyTrcy
 * @Description: 专业
 * @Date: 2022/8/24 8:24 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("专业实体类")
public class Major extends BaseEntity {

  @ApiModelProperty(value = "专业编号",required = true)
  private String code;

  @ApiModelProperty(value = "专业名称",required = true)
  private String name;

  @ApiModelProperty(value = "院系编号",required = true)
  private String deptCode;

  @ApiModelProperty(value = "院系名称",required = true)
  private String deptName;

  @ApiModelProperty(value = "全称",required = true)
  private String fullName;

}
