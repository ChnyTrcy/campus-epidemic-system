package xyz.chnytrcy.campusepidemicsystem.service.pc;

import xyz.chnytrcy.campusepidemicsystem.model.command.pc.city.GetCityListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.City;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.city.GetCityListVO;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: CityService
 * @Author: ChnyTrcy
 * @Description: 城市服务接口
 * @Date: 2022/8/24 3:22 PM
 * @Version: 1.0
 */
public interface CityService extends IService<City> {

  /**
   * 获得城市列表
   */
  Result<List<GetCityListVO>> getCityList(GetCityListCommand command);
}
