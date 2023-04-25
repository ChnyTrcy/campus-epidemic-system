package xyz.chnytrcy.campusepidemicsystem.config.shiro;

import xyz.chnytrcy.campusepidemicsystem.config.shiro.auth.AuthFilter;
import xyz.chnytrcy.campusepidemicsystem.config.shiro.auth.AuthRealm;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/***
 * @Description: ShiroConfig配置
 * @Author: ChnyTrcy
 * @Date: 2022/8/20 6:23 PM
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(AuthRealm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //auth过滤
        Map<String, Filter> filters = Maps.newHashMap();
        filters.put("auth", new AuthFilter());
        shiroFilter.setFilters(filters);
        Map<String, String> filterMap = Maps.newLinkedHashMap();
        // anon匿名访问  auth验证
        // 白名单地址
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/druid/**", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/doc.html", "anon");
        filterMap.put("/campus-epidemic-system/pc/user/img/head_icon","anon");
        filterMap.put("/campus-epidemic-system/pc/user/addUser","anon");
        filterMap.put("/campus-epidemic-system/pc/user/loginByPassword","anon");
        filterMap.put("/campus-epidemic-system/pc/user/forgetPassword","anon");
        filterMap.put("/campus-epidemic-system/app/user/login","anon");
        filterMap.put("/campus-epidemic-system/dict/**","anon");
        filterMap.put("/campus-epidemic-system/pc/user/getCaptchaImg","anon");
        filterMap.put("/campus-epidemic-system/pc/user/getPhoneMessageCaptcha","anon");
        filterMap.put("/campus-epidemic-system/pc/user/loginByPhone","anon");
        filterMap.put("/campus-epidemic-system/pc/user/getCaptchaStatue","anon");
//        getCaptchaStatue
        //websocket路径
        filterMap.put("/ws/chat/**","anon");
        // 除了以上路径，其他都需要权限验证
//        filterMap.put("/**", "anon");
        filterMap.put("/**", "auth");

        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}
