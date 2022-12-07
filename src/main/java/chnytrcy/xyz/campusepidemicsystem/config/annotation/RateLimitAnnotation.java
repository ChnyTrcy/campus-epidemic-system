package chnytrcy.xyz.campusepidemicsystem.config.annotation;

import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.annotation
 * @ClassName: RateLimitAnnotation
 * @Author: ChnyTrcy
 * @Description: 限流注解
 * @Date: 2022/11/20 11:21 AM
 * @Version: 1.0
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitAnnotation {

  BusinessError errorCode() default BusinessError.RATE_LIMIT_BUSY_ERROR;

  int value() default 10;
}