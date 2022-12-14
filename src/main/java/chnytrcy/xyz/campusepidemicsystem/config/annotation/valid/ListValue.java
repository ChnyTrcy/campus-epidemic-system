package chnytrcy.xyz.campusepidemicsystem.config.annotation.valid;

import chnytrcy.xyz.campusepidemicsystem.config.vaild.ListValueConstrainValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @ClassName ListValue
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/20 11:54 AM
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ListValueConstrainValidator.class})
@Documented
public @interface ListValue {

  String message() default "";

  // 支持分组校验
  Class<?>[] groups() default {};

  // 自定义负载信息
  Class<? extends Payload>[] payload() default {};

  // 指定参数，就是上图中指定的可取值的范围
  int [] vals() default {};
}
