package xyz.chnytrcy.campusepidemicsystem.config.vaild;

import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;

/**
 * @ClassName ListValueConstrainValidator
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/20 11:56 AM
 * @Version 1.0
 */
public class ListValueConstrainValidator implements ConstraintValidator<ListValue,Integer> {

  Set<Integer> set = new HashSet<>();

  @Override
  public void initialize(ListValue constraintAnnotation) {
    int[] vals = constraintAnnotation.vals();
    for (int val : vals) {
      set.add(val);
    }
  }

  /**
   * 校验是否匹配
   * @param value 就是需要校验的值
   * @param constraintValidatorContext
   * @return
   */
  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
    return set.contains(value);
  }
}
