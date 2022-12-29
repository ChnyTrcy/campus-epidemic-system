package xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationperson;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.isolation
 * @ClassName: IsolationOperationCommand
 * @Author: ChnyTrcy
 * @Description: 对隔离人员进行隔离操作Command
 * @Date: 2022/9/13 3:11 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("对隔离人员进行隔离操作Command")
public class IsolationOperationCommand extends BaseId {

}
