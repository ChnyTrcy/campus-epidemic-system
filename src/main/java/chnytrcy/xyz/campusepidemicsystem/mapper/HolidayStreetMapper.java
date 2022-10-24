package chnytrcy.xyz.campusepidemicsystem.mapper;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.street.ModifyStreetRiskLevelCommand;
import chnytrcy.xyz.campusepidemicsystem.model.dto.HolidayStreetRiskLevelDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.HolidayStreet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.mapper
 * @InterfaceName: HolidayStreetMapper
 * @Author: ChnyTrcy
 * @Description: 寒暑假返校Mapper
 * @Date: 2022/8/24 3:49 PM
 * @Version: 1.0
 */
@Mapper
public interface HolidayStreetMapper extends BaseMapper<HolidayStreet> {

  /**
   * 获得学生的涉及该风险地区的列表
   * @param command
   * @return
   */
  List<HolidayStreetRiskLevelDTO> queryHolidayToRiskLevel(ModifyStreetRiskLevelCommand command);
}
