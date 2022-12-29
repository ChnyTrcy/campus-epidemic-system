package xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson
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
