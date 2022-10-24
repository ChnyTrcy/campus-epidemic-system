package chnytrcy.xyz.campusepidemicsystem.model.command.app.leave;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.leave
 * @ClassName: QueryLeaveDetailCommand
 * @Author: ChnyTrcy
 * @Description: 查询请假记录详情Command
 * @Date: 2022/10/7 15:51
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("返校Command")
public class QueryLeaveDetailCommand extends BaseId {

}
