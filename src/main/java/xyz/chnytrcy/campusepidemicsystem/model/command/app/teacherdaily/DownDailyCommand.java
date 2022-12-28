package xyz.chnytrcy.campusepidemicsystem.model.command.app.teacherdaily;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.teacherdaily
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
