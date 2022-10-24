package chnytrcy.xyz.campusepidemicsystem.model.entity;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.entity
 * @ClassName: Admin
 * @Author: ChnyTrcy
 * @Description: 管理员
 * @Date: 2022/8/30 9:59 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("管理员实体类")
public class Admin extends BaseId {

  @ApiModelProperty("用户Id")
  private Long userId;

  @ApiModelProperty("院系编号")
  private String deptCode;
}
