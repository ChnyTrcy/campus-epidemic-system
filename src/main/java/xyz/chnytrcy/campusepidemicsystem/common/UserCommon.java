package xyz.chnytrcy.campusepidemicsystem.common;

import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.User;
import xyz.chnytrcy.campusepidemicsystem.model.enums.AuthenticationError;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.chnytrcy.core.config.exception.UserAuthenticationException;
import xyz.chnytrcy.core.config.shiro.utils.HttpContextUtil;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.common
 * @ClassName: UserCommon
 * @Author: ChnyTrcy
 * @Description: 用户常用类
 * @Date: 2022/9/2 10:52 PM
 * @Version: 1.0
 */
@Component
public class UserCommon {

  @Autowired private HttpContextUtil httpContextUtil;

  @Autowired private UserMapper userMapper;

  public String getAccount(){
    Long userId = httpContextUtil.getUserId();
    if(userId == null){
      throw new UserAuthenticationException(AuthenticationError.LOGIN_ACCOUNT_NOT_EXIST_ERROR);
    }
    User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
    if(Objects.isNull(user)){
      throw new UserAuthenticationException(AuthenticationError.LOGIN_ACCOUNT_NOT_EXIST_ERROR);
    }
    return user.getAccount();
  }
}
