package chnytrcy.xyz.campusepidemicsystem.config.shiro.utils;

import chnytrcy.xyz.campusepidemicsystem.config.shiro.SysTokenRepository;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Author chnytrcy
 * @Date 2019/3/31 11:20
 * @Version 1.0
 */
@Component
public class HttpContextUtil {

    @Autowired
    private SysTokenRepository sysTokenRepository;

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getDomain(){
        HttpServletRequest request = getHttpServletRequest();
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
    }

    public static String getOrigin(){
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("Origin");
    }

    public Long getUserId(){
        String token = getToken();
        return sysTokenRepository.findByToken(token).getUserId();
    }

    public String getToken(){
        return getHttpServletRequest().getHeader("Authorization");
    }

    public String getUsername(){
        String token = getToken();
        if(StringUtils.isNotBlank(token)){
            return sysTokenRepository.findByToken(token).getName();
        }
        return null;
    }
}