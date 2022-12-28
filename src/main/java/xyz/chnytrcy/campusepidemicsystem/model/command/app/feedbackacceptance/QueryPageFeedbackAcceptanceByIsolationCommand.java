package xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance
 * @ClassName: QueryPageFeedbackAcceptanceByIsolationCommand
 * @Author: ChnyTrcy
 * @Description: 隔离人员分页查询反馈记录Command
 * @Date: 2022/10/18 11:07
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("隔离人员分页查询反馈记录Command")
public class QueryPageFeedbackAcceptanceByIsolationCommand extends BasePageCommand {

}
