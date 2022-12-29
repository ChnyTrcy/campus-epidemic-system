package xyz.chnytrcy.campusepidemicsystem.config.shiro;

import java.time.LocalDateTime;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.Role;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.User;
import xyz.chnytrcy.campusepidemicsystem.model.enums.AuthenticationError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.LoginTypeEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.SuccessReturnCode;
import xyz.chnytrcy.core.config.shiro.utils.SysTokenRepository;
import xyz.chnytrcy.core.config.shiro.utils.TokenEntity;
import xyz.chnytrcy.core.utils.md5.MD5;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @author chnytrcy
 */
@Component
public class ShiroService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private SysTokenRepository sysTokenRepository;

  //设置几个小时过期
  private final static int CONTINUED_HOUR = 1;
  private final static int CONTINUED_DAY = 30;

  //开发环境设置一个星期过期
  private final static int CONTINUED_HOUR_DEV = 7 * 24;



  public User findByUsername(String username) {
    return userMapper.findUserByName(username);
  }

  /**
   * 创建token
   * @param user 用户
   * @return token值
   */
  public String createToken(User user, LoginTypeEnums type){
    String token1 = MD5.SysMd5(String.valueOf(user.getId()),user.getPassword());
    String token2 = MD5.SysMd5(token1, token1);
    String token = token1 + "-" + token2;
    LocalDateTime nowTime = LocalDateTime.now();
    LocalDateTime expireTime;
    if(type.equals(LoginTypeEnums.PC_PASSWORD) || type.equals(LoginTypeEnums.PC_PHONE)){
      expireTime = nowTime.plusHours(CONTINUED_HOUR);
    }else {
      expireTime = nowTime.plusDays(CONTINUED_DAY);
    }
    TokenEntity tokenEntity = new TokenEntity();
    tokenEntity.setToken(token);
    tokenEntity.setType(type);
    tokenEntity.setExpireTime(expireTime);
    tokenEntity.setUpdateTime(nowTime);
    tokenEntity.setUserId(user.getId());
    tokenEntity.setName(user.getAccount());
    sysTokenRepository.addTokenEntity(tokenEntity);
    return token;
  }

  /**
   * 登出用户
   * @param token 目标token
   * @return 结果
   */
  public Result logout(String token) {
    try {
      sysTokenRepository.delTokenEntityByToken(token);
      return ResultFactory.successResult(SuccessReturnCode.LOGOUT);
    } catch (Exception e) {
      e.printStackTrace();
      return ResultFactory.errorResultAuthentication(AuthenticationError.LOGOUT_ERROR);
    }
  }

  public TokenEntity findByToken(String token){
    if(!StringUtils.isBlank(token)){
      return sysTokenRepository.findByToken(token);
    }else {
      return null;
    }
  }

  /**
   * 根据用户ID获得用户的权限
   * @param id 用户Id
   * @return 结果集
   */
  public List<Role> findRoleListByUserId(Long id){
    return userMapper.findRoleListByUserId(id);
  }
}
