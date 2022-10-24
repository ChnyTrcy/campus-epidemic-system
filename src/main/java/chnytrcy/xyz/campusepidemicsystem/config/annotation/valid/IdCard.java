package chnytrcy.xyz.campusepidemicsystem.config.annotation.valid;

import chnytrcy.xyz.campusepidemicsystem.config.vaild.IdCardConstrainValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.annotation.valid
 * @ClassName: IdCard
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/25 10:34 PM
 * @Version: 1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IdCardConstrainValidator.class})
@Documented
public @interface IdCard {

  String message() default "身份证格式不对";
  // 支持分组校验
  Class<?>[] groups() default {};
  // 自定义负载信息
  Class<? extends Payload>[] payload() default {};
}

