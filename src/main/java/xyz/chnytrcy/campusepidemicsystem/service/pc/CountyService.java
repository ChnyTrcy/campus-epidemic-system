package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.county.GetCountyListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.County;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.county.GetCountyListVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: CountyService
 * @Author: ChnyTrcy
 * @Description: 区县服务接口
 * @Date: 2022/8/24 3:28 PM
 * @Version: 1.0
 */
public interface CountyService extends IService<County> {

  /**
   * 根据城市编号获得区县列表
   */
  Result<List<GetCountyListVO>> getCountyList(GetCountyListCommand command);
}
