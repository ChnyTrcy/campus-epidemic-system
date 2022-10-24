package chnytrcy.xyz.campusepidemicsystem.config.shiro.auto;


import chnytrcy.xyz.campusepidemicsystem.config.exception.UserAuthenticationException;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.ShiroService;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.SysTokenRepository;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.TokenEntity;
import chnytrcy.xyz.campusepidemicsystem.mapper.UserMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.Permission;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.Role;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.model.enums.AuthenticationError;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/***
 * @Description: 用户自定义Shiro的Realm
 * @Author: ChnyTrcy
 * @Date: 2022/8/20 10:54 AM
 */
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SysTokenRepository sysTokenRepository;

    /**
     * 授权 获取用户的角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        User user = (User) principals.getPrimaryPrincipal();
        List<Role> roleListByUserId = shiroService.findRoleListByUserId(user.getId());
        Set<Role> roleSet = new HashSet<>(roleListByUserId);
        user.setRoles(roleSet);
        //2.添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            //2.1添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for (Permission permission : role.getPermissions()) {
                //2.1.1添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }


    /**
     * 认证 判断token的有效性
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1. 根据accessToken，查询用户信息
        TokenEntity tokenEntity = shiroService.findByToken(accessToken);
        //2. token失效
        if (tokenEntity == null || tokenEntity.getExpireTime().isBefore(LocalDateTime.now())) {
            throw new UserAuthenticationException(AuthenticationError.LOGIN_AUTHORIZATION_INVALID_ERROR);
        }
        //3. 调用数据库的方法, 从数据库中查询 userId 对应的用户记录
        User user = userMapper.findUserByUserId(tokenEntity.getUserId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (user == null) {
            throw new UserAuthenticationException(AuthenticationError.LOGIN_ACCOUNT_NOT_EXIST_ERROR);
        }
        //5、延长token持续时间
        tokenEntity.setExpireTime(LocalDateTime.now().plusHours(1));
        tokenEntity.setUpdateTime(LocalDateTime.now());
        sysTokenRepository.updateTokenEntity(tokenEntity);

        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }
}
