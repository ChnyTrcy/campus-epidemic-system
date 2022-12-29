package xyz.chnytrcy.campusepidemicsystem.converter;

import java.util.List;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Dept;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.dept.DeptListVO;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.converter
 * @InterfaceName: DeptConverter
 * @Author: ChnyTrcy
 * @Description: 院系转换器
 * @Date: 2022/12/30 01:25
 * @Version: 1.0
 */
public interface DeptConverter {
  List<DeptListVO> deptEntityToDeptListVo(List<Dept> data);
}
