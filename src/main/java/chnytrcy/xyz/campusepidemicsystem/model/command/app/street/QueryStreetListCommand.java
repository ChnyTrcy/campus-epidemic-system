package chnytrcy.xyz.campusepidemicsystem.model.command.app.street;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.street
 * @ClassName: queryStreetListCommand
 * @Author: ChnyTrcy
 * @Description: 根据区县编号查询街道列表Command
 * @Date: 2022/9/12 2:05 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("根据区县编号查询街道列表Command")
public class QueryStreetListCommand extends Base {

  @ApiModelProperty(value = "区县编号",required = true)
  @Size(max = 6,min = 6,message = "区县编号错误")
  @NotBlank(message = "区县编号不能为空")
  private String code;
}
