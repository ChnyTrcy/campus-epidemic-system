package xyz.chnytrcy.campusepidemicsystem.mapper;

import xyz.chnytrcy.campusepidemicsystem.model.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.mapper
 * @InterfaceName: MenuMapper
 * @Author: ChnyTrcy
 * @Description: 菜单Mapper
 * @Date: 2022/8/23 11:24 PM
 * @Version: 1.0
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

  /**
   * 获取菜单栏
   * @param roleId 角色Id
   * @return 菜单列表
   */
  List<Menu> getMenuList(Integer roleId);
}
