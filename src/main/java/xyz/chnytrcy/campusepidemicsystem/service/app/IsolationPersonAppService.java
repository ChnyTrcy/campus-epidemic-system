package xyz.chnytrcy.campusepidemicsystem.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationperson.IsolationOperationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationperson.LiftIsolationOperationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.dto.QueryPageIsolationPersonByEpidemicDTO;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app
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
