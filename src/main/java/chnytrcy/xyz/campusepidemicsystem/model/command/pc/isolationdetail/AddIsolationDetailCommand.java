package chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail;

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
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail
 * @ClassName: AddIsolationDetailCommand
 * @Author: ChnyTrcy
 * @Description: 添加隔离人员体温监测Command
 * @Date: 2022/9/1 5:07 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("添加隔离人员体温监测Command")
public class AddIsolationDetailCommand extends BaseId {

  @ApiModelProperty(value = "体温（去掉小数点，即乘10后的结果）",required = true)
  @NotNull(message = "体温不能为空")
  @Min(value = 350,message = "体温超过限制范围")
  @Max(value = 450,message = "体温超过限制范围")
  private Integer temperature;
}
