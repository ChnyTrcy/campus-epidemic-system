package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.mapper.CityMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.city.GetCityListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.City;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.city.GetCityListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.CityService;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
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
