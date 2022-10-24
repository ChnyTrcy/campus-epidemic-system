package chnytrcy.xyz.campusepidemicsystem.utils.dict;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.EnumKey;
import java.lang.reflect.Method;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.dict
 * @ClassName: AnnotationValidRegistrar
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/10/20 20:16
 * @Version: 1.0
 */
@Component
public class AnnotationValidRegistrar implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
    if (methods != null) {
      for (Method method : methods) {
        EnumKey jindalong = AnnotationUtils.findAnnotation(method, EnumKey.class);
        // process
        if (jindalong != null) {
          System.out.println(method.getName());
          System.out.println(jindalong.type());
        }

      }
    }
    return bean;
  }

}
