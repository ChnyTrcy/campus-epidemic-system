package chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageCommand;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail
 * @ClassName: QueryPageIsolationDetailCommand
 * @Author: ChnyTrcy
 * @Description: 分页查询隔离人员隔离记录Command
 * @Date: 2022/10/18 09:53
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询隔离人员隔离记录Command")
public class QueryPageIsolationDetailCommand extends BasePageCommand {

}
