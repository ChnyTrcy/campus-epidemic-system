package chnytrcy.xyz.campusepidemicsystem.model.command.app.teacherdaily;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.teacherdaily
 * @ClassName: UpDailyCommand
 * @Author: ChnyTrcy
 * @Description: 打卡上班Command
 * @Date: 2022/9/6 2:32 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("打卡上班Command")
public class UpDailyCommand extends Base {

  @ApiModelProperty(value = "打卡结果（0:绿，1:黄，2:红）",required = true)
  @NotNull(message = "打卡结果不能为空")
  @ListValue(vals = {0,1,2},message = "打卡结果错误")
  private Integer result;
}
