package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.menu
 * @ClassName: MenuBaseVO
 * @Author: ChnyTrcy
 * @Description: 菜单基本VO类
 * @Date: 2022/8/24 12:33 AM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("菜单VO")
public class MenuBaseVO extends Base {

  @ApiModelProperty("唯一ID")
  private Integer countId;

  @ApiModelProperty("内容")
  private String title;

  @ApiModelProperty("图标")
  private String icon;

  @ApiModelProperty("URL路径")
  private String path;

  @ApiModelProperty("子类")
  private List<MenuBaseVO> children;
}
