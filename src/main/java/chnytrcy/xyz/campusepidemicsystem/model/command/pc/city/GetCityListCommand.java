package chnytrcy.xyz.campusepidemicsystem.model.command.pc.city;

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
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.city
 * @ClassName: GetCityListCommand
 * @Author: ChnyTrcy
 * @Description: 获取城市列表Command
 * @Date: 2022/8/28 5:39 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("获取城市列表Command")
public class GetCityListCommand extends Base {

  @ApiModelProperty(value = "省份编号",required = true)
  @NotBlank(message = "省份编号不能为空")
  @Size(max = 2,min = 2,message = "省份编号长度错误")
  private String provinceCode;
}
