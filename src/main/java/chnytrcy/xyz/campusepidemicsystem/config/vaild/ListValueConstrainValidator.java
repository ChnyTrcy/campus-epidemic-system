package chnytrcy.xyz.campusepidemicsystem.config.vaild;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName ListValueConstrainValidator
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/20 11:56 AM
 * @Version 1.0
 */
// 实现ConstraintValidator接口，泛型值：<自定义注解类，被校验值的数据类型>
public class ListValueConstrainValidator implements ConstraintValidator<ListValue,Integer> {
  // 整体思路，使用set在initialize获得参数信息，在isValid方法中校验，成功true，失败false
  Set<Integer> set = new HashSet<>();
  // 初始化方法,可以得到详细信息
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
