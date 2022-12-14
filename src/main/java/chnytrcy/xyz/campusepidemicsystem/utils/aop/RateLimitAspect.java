package chnytrcy.xyz.campusepidemicsystem.utils.aop;

import ch.qos.logback.core.util.TimeUtil;
import chnytrcy.xyz.campusepidemicsystem.config.annotation.RateLimitAnnotation;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import com.google.common.util.concurrent.RateLimiter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop
 * @ClassName: RateLimitAspect
 * @Author: ChnyTrcy
 * @Description: 流量拦截控制器
 * @Date: 2022/11/21 14:27
 * @Version: 1.0
 */

@Aspect
@Slf4j
@Component
@Order(value = 10)
public class RateLimitAspect {

  /**
   * 流量阈值(默认是10)
   */
  private static double count;

  /**
   * 流量桶
   */
  private static final AtomicInteger reqCount = new AtomicInteger();

  /**
   * 时间周期（默认1000毫秒）
   */
  private static final long interval = 1000;

  /**
   * 周期起始点
   */
  private static long start = System.currentTimeMillis();

  @Pointcut(value = "@annotation(chnytrcy.xyz.campusepidemicsystem.config.annotation.RateLimitAnnotation)")
  public void rateLimit(){}

  @Around("rateLimit()")
  public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
    log.debug("限流注解开始了！");
    Signature signature = joinPoint.getSignature();
    MethodSignature msg=(MethodSignature) signature;
    Object target = joinPoint.getTarget();
    Method method = target.getClass().getMethod(msg.getName(), msg.getParameterTypes());
    RateLimitAnnotation annotation = method.getAnnotation(RateLimitAnnotation.class);
    count = annotation.value();
    if(canPass()){
      return joinPoint.proceed();
    }else {
      throw new BusinessException(annotation.errorCode());
    }
  }

  protected static Boolean canPass(){
    if(start + interval > System.currentTimeMillis()){
      return reqCount.incrementAndGet() <= count;
    }else {
      reqCount.set(0);
      start = System.currentTimeMillis();
      return false;
    }

  }


}
