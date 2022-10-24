package chnytrcy.xyz.campusepidemicsystem.common;

import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.utils.HttpContextUtil;
import chnytrcy.xyz.campusepidemicsystem.mapper.AdminMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Admin;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: AdminCommon
 * @Author: ChnyTrcy
 * @Description: 管理员常用类
 * @Date: 2022/8/30 3:53 PM
 * @Version: 1.0
 */
@Component
public class AdminCommon {

  @Autowired private AdminMapper adminMapper;

  @Autowired private HttpContextUtil httpContextUtil;

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
