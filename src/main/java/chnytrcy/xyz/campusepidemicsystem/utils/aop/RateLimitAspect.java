package chnytrcy.xyz.campusepidemicsystem.utils.aop;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.RateLimitAnnotation;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import com.google.common.util.concurrent.RateLimiter;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop
 * @ClassName: RateLimitAspect
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/21 14:27
 * @Version: 1.0
 */

@Aspect
@Component
public class RateLimitAspect {

  @Pointcut(value = "@annotation(chnytrcy.xyz.campusepidemicsystem.config.annotation.RateLimitAnnotation)")
  public void rateLimit(){}

  @Around("rateLimit()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    Signature signature = joinPoint.getSignature();
    MethodSignature msg=(MethodSignature) signature;
    Object target = joinPoint.getTarget();
    Method method = target.getClass().getMethod(msg.getName(), msg.getParameterTypes());
    RateLimitAnnotation annotation = method.getAnnotation(RateLimitAnnotation.class);
    //限流每秒1次。如果设置为n，则每秒限流为1/n
    RateLimiter rateLimiter= RateLimiter.create(annotation.value());
    boolean flag = rateLimiter.tryAcquire();
    Object obj = null;
    try {
      if (flag) {
        obj = joinPoint.proceed();
      }else{
        throw new BusinessException(annotation.errorCode());
      }
    } catch (Throwable e) {
      throw e;
    }
    return obj;
  }
}
