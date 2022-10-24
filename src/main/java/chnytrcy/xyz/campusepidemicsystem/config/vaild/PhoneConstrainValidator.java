package chnytrcy.xyz.campusepidemicsystem.config.vaild;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.Phone;
import cn.hutool.core.lang.Validator;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName ListValueConstrainValidator
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/25 10:29 PM
 * @Version 1.0
 */
// 实现ConstraintValidator接口，泛型值：<自定义注解类，被校验值的数据类型>
public class PhoneConstrainValidator implements ConstraintValidator<Phone,String> {

  /**
   * 校验是否匹配
   * @param value 就是需要校验的值
   * @param constraintValidatorContext
   * @return
   */
  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if(StringUtils.isBlank(value)){
      return true;
    }
    return Validator.isMobile(value);
  }
}
