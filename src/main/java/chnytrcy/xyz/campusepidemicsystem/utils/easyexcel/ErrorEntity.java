package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel
 * @ClassName: ErrorEntity
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/29 15:49
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class ErrorEntity extends Base {

  @ApiModelProperty("行数")
  private Integer row;

  @ApiModelProperty("解析内容（JSON）")
  private String content;

  @ApiModelProperty("错误内容")
  private String errorContent;

}
