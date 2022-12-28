package xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.isolationperson
 * @ClassName: IsolationCommand
 * @Author: ChnyTrcy
 * @Description: 隔离Command
 * @Date: 2022/8/31 4:43 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("隔离Command")
public class IsolationCommand extends BaseId {

}
