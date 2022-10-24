package chnytrcy.xyz.campusepidemicsystem.model.command.pc.county;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
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
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.county
 * @ClassName: GetCountyListCommand
 * @Author: ChnyTrcy
 * @Description: 根据城市编号获取区县列表Command
 * @Date: 2022/8/28 5:48 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("根据城市编号获取区县列表Command")
public class GetCountyListCommand extends Base {

  @ApiModelProperty(value = "城市编号",required = true)
  @NotBlank(message = "城市编号不能为空")
  @Size(max = 4,min = 4,message = "城市编号长度错误")
  private String cityCode;
}
