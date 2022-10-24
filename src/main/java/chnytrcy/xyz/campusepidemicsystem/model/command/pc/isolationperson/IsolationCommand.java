package chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationperson;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.isolationperson
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
