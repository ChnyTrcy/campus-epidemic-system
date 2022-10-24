package chnytrcy.xyz.campusepidemicsystem.service.app;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageCommand;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationperson.IsolationOperationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationperson.LiftIsolationOperationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.dto.QueryPageIsolationPersonByEpidemicDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app
 * @InterfaceName: IsolationPersonAppService
 * @Author: ChnyTrcy
 * @Description: 隔离人员服务接口
 * @Date: 2022/9/10 4:38 PM
 * @Version: 1.0
 */

public interface IsolationPersonAppService extends IService<IsolationPerson> {

  /**
   * 防疫人员分页查询对应的隔离人员
   */
  Result<BasePageVO<QueryPageIsolationPersonByEpidemicDTO>> queryPageIsolationPersonByEpidemic(
      BasePageCommand command);

  /**
   * 对待隔离人员进行隔离操作
   */
  Result<Void> isolationOperation(IsolationOperationCommand command);

  /**
   * 对隔离人员进行解除隔离操作
   */
  Result<Void> liftIsolationOperation(LiftIsolationOperationCommand command);
}
