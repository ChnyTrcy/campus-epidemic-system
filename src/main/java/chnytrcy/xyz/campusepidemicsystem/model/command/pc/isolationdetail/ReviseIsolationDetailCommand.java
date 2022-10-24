package chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail
 * @ClassName: ReviseIsolationDetailCommand
 * @Author: ChnyTrcy
 * @Description: 修改某一跳隔离体温记录Command
 * @Date: 2022/9/2 9:13 AM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改某一跳隔离体温记录Command")
public class ReviseIsolationDetailCommand extends BaseId {

  @ApiModelProperty(value = "体温（去掉小数点，即乘10后的结果）",required = true)
  @NotNull(message = "体温不能为空")
  @Min(value = 350,message = "体温超过限制范围")
  @Max(value = 450,message = "体温超过限制范围")
  private Integer temperature;

}
