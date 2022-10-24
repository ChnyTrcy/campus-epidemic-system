package chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationperson;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationperson.ReleaseQuarantineCommand;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationperson
 * @ClassName: LiftIsolationOperationCommand
 * @Author: ChnyTrcy
 * @Description: 解除隔离操作Command
 * @Date: 2022/9/13 4:25 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("解除隔离操作Command")
public class LiftIsolationOperationCommand extends ReleaseQuarantineCommand {

}
