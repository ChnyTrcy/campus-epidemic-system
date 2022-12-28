package xyz.chnytrcy.campusepidemicsystem.common;

import xyz.chnytrcy.campusepidemicsystem.mapper.AdminMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Admin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.config.shiro.utils.HttpContextUtil;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.common
 * @ClassName: AdminCommon
 * @Author: ChnyTrcy
 * @Description: 管理员常用类
 * @Date: 2022/8/30 3:53 PM
 * @Version: 1.0
 */
@Component
public class AdminCommon {

  @Autowired private AdminMapper adminMapper;

  @Resource private HttpContextUtil httpContextUtil;

  /**
   * 获得管理员Id
   * @return Id
   */
  public Long getAdminId(){
    Admin admin = adminMapper.selectOne(new LambdaQueryWrapper<Admin>()
        .eq(Admin::getUserId, httpContextUtil.getUserId()));
    if(null == admin){
      throw new BusinessException(BusinessError.ADMIN_IS_NOT_EXIST_ERROR);
    }
    return admin.getUserId();
  }

  /**
   * 根据管理员的院系编号获得唯一的管理员账号
   * @param dept 院系编号
   */
  public Long getAdminCodeByDept(String dept){
    return adminMapper.selectOne(new LambdaQueryWrapper<Admin>()
        .eq(Admin::getDeptCode,dept)).getUserId();
  }
}
