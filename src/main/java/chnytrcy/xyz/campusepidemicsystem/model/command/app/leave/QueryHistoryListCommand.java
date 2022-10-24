package chnytrcy.xyz.campusepidemicsystem.model.command.app.leave;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageCommand;
import io.swagger.annotations.ApiModel;
import java.util.concurrent.ExecutorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.leave
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
