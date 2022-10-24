package chnytrcy.xyz.campusepidemicsystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.entity
 * @ClassName: Menu
 * @Author: ChnyTrcy
 * @Description: 菜单实体类
 * @Date: 2022/8/23 11:00 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "菜单实体类")
public class Menu {

  @ApiModelProperty(value = "id")
  private Integer id;

  @ApiModelProperty(value = "父级id")
  private Integer pid;

  @ApiModelProperty(value = "标签")
  private String title;

  @ApiModelProperty(value = "图标")
  private String icon;

  @ApiModelProperty(value = "跳转路径")
  private String path;

  @ApiModelProperty(value = "用户类型")
  private Integer type;

  @ApiModelProperty(value = "排序值")
  @TableField("order_value")
  private Integer orderValue;

  @TableField(exist = false)
  private List<Menu> children;
}
