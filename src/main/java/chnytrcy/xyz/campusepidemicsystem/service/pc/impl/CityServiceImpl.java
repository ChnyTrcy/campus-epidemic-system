package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.CityMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.city.GetCityListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.City;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.city.GetCityListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.CityService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: CityServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 3:22 PM
 * @Version: 1.0
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

  @Override
  public Result<List<GetCityListVO>> getCityList(GetCityListCommand command) {
    List<City> cities = getBaseMapper().selectList(new LambdaQueryWrapper<City>()
        .eq(City::getProvinceCode, command.getProvinceCode()));
    List<GetCityListVO> key = DozerUtils.convertList(cities, GetCityListVO.class);
    return ResultFactory.successResult(key);
  }
}
