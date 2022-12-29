package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.command.pc.street.QueryPageStreetCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Street;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: StreetMapper
 * @Author: ChnyTrcy
 * @Description: 街道Mapper
 * @Date: 2022/8/24 4:54 PM
 * @Version: 1.0
 */
@Mapper
public interface StreetMapper extends BaseMapper<Street> {

  List<Street> queryPageStreet(
      @Param("command") QueryPageStreetCommand command,
      @Param("length") Integer length);

  /**
   * 获取高风险地区数量
   */
  Integer countHighStreet();

  /**
   * 获取中风险地区数量
   */
  Integer countMidStreet();
}
