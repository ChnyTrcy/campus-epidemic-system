package xyz.chnytrcy.campusepidemicsystem.converter.impl;

import xyz.chnytrcy.campusepidemicsystem.converter.MenuConverter;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Menu;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.menu.MenuBaseVO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.converter.impl
 * @ClassName: MenuConverterImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 12:32 AM
 * @Version: 1.0
 */
@Component
public class MenuConverterImpl implements MenuConverter {

  @Override
  public List<MenuBaseVO> MenuListToMenuBaseVoList(List<Menu> list) {
    List<MenuBaseVO> key = new ArrayList<>();
    if(null == list || list.isEmpty()){
      return null;
    }
    for (Menu menu : list) {
      key.add(menuToMenuBaseVO(menu));
    }
    return key;
  }

  private MenuBaseVO menuToMenuBaseVO(Menu menu){
    if(null == menu){
      return null;
    }
    MenuBaseVO vo = new MenuBaseVO();
    vo.setCountId(menu.getId());
    vo.setPath(menu.getPath());
    vo.setIcon(menu.getIcon());
    vo.setTitle(menu.getTitle());
    vo.setChildren(MenuListToMenuBaseVoList(menu.getChildren()));
    return vo;
  }
}
