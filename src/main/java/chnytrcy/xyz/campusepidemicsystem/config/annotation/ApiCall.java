package chnytrcy.xyz.campusepidemicsystem.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 限流注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiCall {

  /**
   * 单位时间能访问多少次，默认不限次数
   */
  long limitCount() default 1000000000;

  /**
   * 多长时间内限制，默认 60
   */
  long time() default 60;

  /**
   * 时间类型，默认为毫秒
   */
  TimeUnit timeUnit() default TimeUnit.MILLISECONDS;
}

