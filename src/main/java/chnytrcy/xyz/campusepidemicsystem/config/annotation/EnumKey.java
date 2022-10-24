package chnytrcy.xyz.campusepidemicsystem.config.annotation;

import chnytrcy.xyz.campusepidemicsystem.model.enums.EnumsEntityType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.annotation
 * @ClassName: EnumKey
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/20 11:21 AM
 * @Version: 1.0
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumKey {
  String name();

  String desc() default "";

  EnumsEntityType type();
}
