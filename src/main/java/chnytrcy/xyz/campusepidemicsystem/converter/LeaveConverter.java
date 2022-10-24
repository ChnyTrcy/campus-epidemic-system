package chnytrcy.xyz.campusepidemicsystem.converter;

import chnytrcy.xyz.campusepidemicsystem.model.dto.QueryPageLeaveDTO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave.QueryPageLeaveListVO;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.converter
 * @InterfaceName: LeaveConverter
 * @Author: ChnyTrcy
 * @Description: 离校类型转换器
 * @Date: 2022/9/3 9:46 PM
 * @Version: 1.0
 */
public interface LeaveConverter {

  /**
   * 自定义转换器
   * @param data
   * @return
   */
  List<QueryPageLeaveListVO> converter(List<QueryPageLeaveDTO> data);
}
