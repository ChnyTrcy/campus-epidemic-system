package chnytrcy.xyz.campusepidemicsystem.utils.aop;

import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import java.lang.reflect.Parameter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.aop
 * @ClassName: ControllerLogAspect
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/10/18 21:45
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
@Order(value = 99)
public class ControllerLogAspect {

  static {
    log.info("日志打印服务注入成功...");
  }

  @Value("${controller.logs.switch}")
  private boolean key;

  @Around("execution(public * chnytrcy.xyz.campusepidemicsystem.controller..*.*(..))")
  public Object controllerLog(ProceedingJoinPoint pdj) throws Throwable{
    if(key){
      HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
      long startTime = System.currentTimeMillis();
      Object[] args = pdj.getArgs();
      Object ret = pdj.proceed(args);
      long endTime = System.currentTimeMillis();
      Result result = (Result) ret;
      StringBuilder sb = new StringBuilder();
      sb.append("==>请求URL: " + request.getRequestURL().toString());
      sb.append("   ==>请求时间: " + LocalDateTime.now());
      sb.append("   ==>请求IP: " + request.getRemoteAddr());
      sb.append("   ==>调用方法: " + pdj.getSignature().getDeclaringTypeName() + "." + pdj.getSignature().getName());
//    sb.append("   ==>请求参数: " + );
      if(ObjectUtil.isNotNull(result)){
        sb.append("   ==>是否成功返回:" + result.getSuccess());
      }
      sb.append("   ==>耗时: " + (endTime - startTime) + "毫秒");
      log.info(sb.toString());
      return ret;
    }else {
      return pdj.proceed();
    }
  }
}
