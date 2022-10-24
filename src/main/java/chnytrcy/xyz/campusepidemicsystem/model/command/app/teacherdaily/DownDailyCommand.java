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
 * @Description: 打卡下班Command
 * @Date: 2022/9/10 2:32 PM
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("打卡下班Command")
public class DownDailyCommand extends Base {

}
