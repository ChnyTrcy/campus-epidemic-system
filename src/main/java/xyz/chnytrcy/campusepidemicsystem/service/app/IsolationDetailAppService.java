package xyz.chnytrcy.campusepidemicsystem.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.AddTemperatureCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.InsertDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.QueryPageIsolationDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationDetail;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.AddTemperatureVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.QueryIsolationTimeVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.QueryPageIsolationDetailVO;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app
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

  /**
   * 添加每日健康打卡记录
   */
  Result<Void> insertDetail(InsertDetailCommand command);

  /**
   * 获取隔离开始/结束时间
   */
  Result<QueryIsolationTimeVO> queryIsolationTime();
}
