package xyz.chnytrcy.campusepidemicsystem.model.command.app.leave;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.leave
 * @ClassName: QueryHistoryListCommand
 * @Author: ChnyTrcy
 * @Description: 查询自己的历史请假记录Command
 * @Date: 2022/10/7 15:31
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("查询自己的历史请假记录Command")
public class QueryHistoryListCommand extends BasePageCommand {

}
