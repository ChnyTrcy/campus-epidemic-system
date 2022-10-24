package chnytrcy.xyz.campusepidemicsystem.service.app;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail.AddTemperatureCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail.QueryPageIsolationDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationDetail;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationdetail.AddTemperatureVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationdetail.QueryPageIsolationDetailVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app
 * @InterfaceName: IsolationDetailAppService
 * @Author: ChnyTrcy
 * @Description: 移动端 - 隔离记录服务接口
 * @Date: 2022/9/13 3:26 PM
 * @Version: 1.0
 */
public interface IsolationDetailAppService extends IService<IsolationDetail> {

  /**
   * 添加隔离人员体温
   */
  Result<AddTemperatureVO> addTemperature(AddTemperatureCommand command);

  /**
   * 分页查询隔离人员隔离记录
   */
  Result<BasePageVO<QueryPageIsolationDetailVO>> queryPageIsolationDetail(
      QueryPageIsolationDetailCommand command);
}
