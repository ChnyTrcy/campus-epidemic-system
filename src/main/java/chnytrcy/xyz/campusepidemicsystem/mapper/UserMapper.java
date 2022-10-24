package chnytrcy.xyz.campusepidemicsystem.mapper;

import chnytrcy.xyz.campusepidemicsystem.model.dto.AdminInformationDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.Role;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @ClassName UserMapper
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/20 6:05 PM
 * @Version 1.0
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

  /**
   * 根据用户名查找用户
   * @param account 用户名
   * @return 结果
   */
  User findUserByName(String account);

  /**
   * 根据用户名获得唯一编号
   * @param name 用户名
   * @return 唯一编号
   */
  String selectAutoIdByName(String name);

  /**
   * 获得用户信息
   * @param id 用户唯一编号
   * @return 用户信息
   */
  User getInformation(String id);

  /**
   * 根据用户Id查找角色列表
   * @param id 用户ID
   * @return 结果集
   */
  List<Role> findRoleListByUserId(Long id);

  /**
   * 根据用户Id查找用户
   * @param id 用户Id
   * @return 用户
   */
  User findUserByUserId(Long id);

  /**
   * 根据用户Id获取角色对象
   * @param userId id
   * @return 角色对象
   */
  Role getRoleByUserId(Long userId);

  /**
   * 添加用户和角色关联表
   * @param id
   * @param i
   */
  void addUserRole(Long id, int i);

  /**
   * 删除用户和角色关联表
   * @param id 用户ID标识符
   */
  int deleteUserToRole(Long id);

  /**
   * 修改用户和角色关联表
   * @param userId 用户ID
   * @param i 角色编号
   */
  void updateRoleByUserId(
      @Param("userId") Long userId,
      @Param("i") int i);

  /**
   * 获取管理员基本信息
   */
  AdminInformationDTO queryAdminInformation(User user);
}
