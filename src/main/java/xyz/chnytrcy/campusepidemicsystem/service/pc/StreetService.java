package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.street.BatchModifyStreetRiskLevelCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.street.ModifyStreetRiskLevelCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.street.QueryPageStreetCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Street;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.street.QueryPageStreetVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.street.QueryStatisticsStreetVO;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: StreetService
 * @Author: ChnyTrcy
 * @Description: 街道服务接口
 * @Date: 2022/8/24 4:54 PM
 * @Version: 1.0
 */
public interface StreetService extends IService<Street> {

  /**
   * 分页查询街道
   * @param command
   * @return
   */
  Result<BasePageVO<QueryPageStreetVO>> queryPageStreet(QueryPageStreetCommand command);

  /**
   * 修改街道的风险等级
   */
  Result<Void> modifyStreetRiskLevel(ModifyStreetRiskLevelCommand command);

  /**
   * 批量修改地区的风险等级
   */
  Result<Void> batchModifyStreetRiskLevel(BatchModifyStreetRiskLevelCommand command);

  /**
   * 统计各风险地区数量
   */
  Result<QueryStatisticsStreetVO> queryStatisticsStreet();
}
