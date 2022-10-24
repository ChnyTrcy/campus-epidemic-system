package chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.isolationdetail
 * @ClassName: QueryIsolationDetailByIdCommand
 * @Author: ChnyTrcy
 * @Description: 根据隔离人员Id查询隔离记录command
 * @Date: 2022/9/1 11:18 AM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("根据隔离人员Id查询隔离记录command")
public class QueryIsolationDetailByIdCommand extends BaseId {

}
