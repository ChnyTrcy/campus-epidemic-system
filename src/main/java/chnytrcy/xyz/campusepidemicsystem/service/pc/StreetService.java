package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.street.BatchModifyStreetRiskLevelCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.street.ModifyStreetRiskLevelCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.street.QueryPageStreetCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Street;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.street.QueryPageStreetVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.street.QueryStatisticsStreetVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
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
  Result<BasePageVO<QueryPageStreetVO>>queryPageStreet(QueryPageStreetCommand command);

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
