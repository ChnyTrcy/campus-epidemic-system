package chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail
 * @ClassName: AddTemperatureCommand
 * @Author: ChnyTrcy
 * @Description: 增加隔离人员体温记录Command
 * @Date: 2022/9/13 3:31 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("增加隔离人员体温记录Command")
public class AddTemperatureCommand extends BaseId {

  @ApiModelProperty(value = "体温(乘10后的结果)",required = true)
  @Min(value = 350,message = "体温超过限制范围")
  @Max(value = 450,message = "体温超过限制范围")
  @NotNull(message = "体温不能为空")
  private Integer temperature;
}
