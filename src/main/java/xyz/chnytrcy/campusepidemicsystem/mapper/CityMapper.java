package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: CityMapper
 * @Author: ChnyTrcy
 * @Description: 城市Mapper
 * @Date: 2022/8/24 3:21 PM
 * @Version: 1.0
 */
@Mapper
public interface CityMapper extends BaseMapper<City> {

}
