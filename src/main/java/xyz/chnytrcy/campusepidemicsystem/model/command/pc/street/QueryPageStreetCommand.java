package xyz.chnytrcy.campusepidemicsystem.model.command.pc.street;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.street
 * @ClassName: QueryPageStreetCommand
 * @Author: ChnyTrcy
 * @Description: 分页查询街道Command
 * @Date: 2022/8/28 7:42 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询街道Command")
public class QueryPageStreetCommand extends BasePageCommand {

  @ApiModelProperty(value = "地区编号",required = true)
  private String code;

  @ApiModelProperty("风险等级")
  @ListValue(vals = {0,1,2},message = "风险等级错误")
  @NotNull(message = "风险等级不能为空")
  private Integer riskLevel;
}
