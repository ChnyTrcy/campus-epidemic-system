package xyz.chnytrcy.campusepidemicsystem.model.command.pc.street;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.street
 * @ClassName: ModifyStreetRiskLevelCommand
 * @Author: ChnyTrcy
 * @Description: 修改地区的风险等级Command
 * @Date: 2022/8/29 3:00 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改地区的风险等级Command")
public class ModifyStreetRiskLevelCommand extends Base {

  @ApiModelProperty(value = "街区编号",required = true)
  @NotBlank(message = "街区编号不能为空")
  @Size(max = 9,min = 9,message = "街区编号长度错误")
  private String streetCode;

  @ApiModelProperty(value = "风险等级",required = true)
  @NotNull(message = "风险等级不能为空")
  @ListValue(vals = {0,1,2},message = "风险等级错误")
  private Integer riskLevel;
}
