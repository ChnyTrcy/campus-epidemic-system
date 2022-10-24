package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.county.GetCountyListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.County;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.county.GetCountyListVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
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
