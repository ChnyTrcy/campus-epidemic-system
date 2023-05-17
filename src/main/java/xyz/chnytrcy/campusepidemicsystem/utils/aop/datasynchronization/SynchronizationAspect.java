package xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization;

import cn.hutool.core.util.ObjectUtil;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.chnytrcy.campusepidemicsystem.utils.mq.producter.SynchronizationProducer;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronizatiion
 * @ClassName: SynchronizationAspect
 * @Author: ChnyTrcy
 * @Description: 数据全量同步切面
 * @Date: 2022/12/2 15:26
 * @Version: 1.0
 */
@Aspect
@Component
@Slf4j
public class SynchronizationAspect {

  @Autowired private SynchronizationProducer producer;

  @Value("${aop.synchronization.data.switch}")
  private Boolean aopSwitch;

  private final List<String> allowAspList = Stream.of(
      "MAJOR",
      "STUDENT",
      "DEPT",
      "CLASS_ENTITY",
      "TEACHER"
  ).collect(Collectors.toList());

  @Pointcut(value = "@annotation(xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.DataSynchronous)")
  public void point(){};

  @AfterReturning("point()")
  public void synchronization(JoinPoint joinPoint) throws Throwable {
    if(!aopSwitch){
      return;
    }
    Method method = null;
    log.info("进入全量数据同步拦截器");
    Class<?> clazz = joinPoint.getTarget().getClass();
    String methodName = joinPoint.getSignature().getName();
    Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod()
        .getParameterTypes();
    method = clazz.getMethod(methodName, parameterTypes);
    if(ObjectUtil.isNull(method)){
      log.warn("数据同步拦截器切面异常!");
    }
    DataSynchronous annotation = method.getAnnotation(DataSynchronous.class);
    if(ObjectUtil.isNull(annotation)){
      log.warn("获取注解失败!");
    }
    String tableName = annotation.type().name();
    if(!allowAspList.contains(tableName)){
      log.warn("不支持该表的全量数据同步服务!");
    }
    producer.addDataSynchronousEntrance(annotation.type());
  }

}
