package chnytrcy.xyz.campusepidemicsystem.converter;

import chnytrcy.xyz.campusepidemicsystem.model.entity.Menu;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.menu.MenuBaseVO;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.converter
 * @InterfaceName: MenuConverter
 * @Author: ChnyTrcy
 * @Description: 菜单转换器接口
 * @Date: 2022/8/24 12:31 AM
 * @Version: 1.0
 */
public interface MenuConverter {

  List<MenuBaseVO> MenuListToMenuBaseVoList(List<Menu> list);
}
