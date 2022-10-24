package chnytrcy.xyz.campusepidemicsystem.config.annotation.valid;

import chnytrcy.xyz.campusepidemicsystem.config.vaild.PhoneConstrainValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @ClassName Phone
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/25 10:31 PM
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PhoneConstrainValidator.class})
@Documented
public @interface Phone {

  String message() default "手机号码格式不对";
  // 支持分组校验
  Class<?>[] groups() default {};
  // 自定义负载信息
  Class<? extends Payload>[] payload() default {};
}
