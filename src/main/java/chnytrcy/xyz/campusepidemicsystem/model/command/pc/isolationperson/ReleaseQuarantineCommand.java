package chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationperson;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationperson
 * @ClassName: ReleaseQuarantineCommand
 * @Author: ChnyTrcy
 * @Description: 解除隔离Command
 * @Date: 2022/9/2 9:28 AM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("解除隔离Command")
public class ReleaseQuarantineCommand extends BaseId {

}
