package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.converter.MenuConverter;
import xyz.chnytrcy.campusepidemicsystem.mapper.MenuMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Menu;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.Role;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.menu.MenuBaseVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.MenuService;
import xyz.chnytrcy.core.config.shiro.utils.HttpContextUtil;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
 * @ClassName: SystemServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/23 11:23 PM
 * @Version: 1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

  @Autowired private HttpContextUtil httpContextUtil;

  @Autowired private UserMapper userMapper;

  @Autowired private MenuConverter menuConverter;

  @Override
  public Result<List<MenuBaseVO>> menuList() {
    //1、获得当前用户ID
    Long userId = httpContextUtil.getUserId();
    //2、获得当前用户的角色ID
    Role role = userMapper.getRoleByUserId(userId);
    Integer roleId = role.getRoleId();
    //3、查询结果
    List<Menu> menuList = getBaseMapper().getMenuList(roleId);
    //4、类型转换
    List<MenuBaseVO> key = menuConverter.MenuListToMenuBaseVoList(menuList);
    return ResultFactory.successResult(key);
  }
}
