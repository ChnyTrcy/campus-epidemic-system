package chnytrcy.xyz.campusepidemicsystem.utils.aop;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop
 * @ClassName: ApiCallAspect
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/21 05:11
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class ApiCallAspect {

  @Autowired private RedisTemplate redisTemplate;

  public static final String FORMAT_PATTERN_DAY = "yyyy-MM-dd";

  public static final String FORMAT_PATTERN_MILLS = "yyyy-MM-dd HH:mm:ss:SSS";

  @Pointcut("@annotation(chnytrcy.xyz.campusepidemicsystem.config.annotation.ApiCall)")
  public void apiCall(){}

  @Before("apiCall()")
  public void before(){
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    HttpServletRequest request = attributes.getRequest();

    String uri = request.getRequestURI();

  }

}
