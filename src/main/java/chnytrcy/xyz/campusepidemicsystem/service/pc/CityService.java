package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.city.GetCityListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.City;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.city.GetCityListVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
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
