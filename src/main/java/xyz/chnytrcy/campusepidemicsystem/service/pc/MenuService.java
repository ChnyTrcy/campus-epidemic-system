package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Menu;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.menu.MenuBaseVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: SystemService
 * @Author: ChnyTrcy
 * @Description: 系统服务接口
 * @Date: 2022/8/23 11:23 PM
 * @Version: 1.0
 */
public interface MenuService extends IService<Menu> {

  /**
   * 获取菜单列表
   * @return 结果
   */
  Result<List<MenuBaseVO>> menuList();
}
