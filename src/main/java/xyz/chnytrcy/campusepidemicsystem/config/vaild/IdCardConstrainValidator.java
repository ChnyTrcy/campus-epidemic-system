package xyz.chnytrcy.campusepidemicsystem.config.vaild;

import cn.hutool.core.util.IdcardUtil;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;
import xyz.chnytrcy.core.config.annotation.valid.IdCard;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.config.vaild
 * @ClassName: IdCardConstrainValidator
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/25 10:35 PM
 * @Version: 1.0
 */
public class IdCardConstrainValidator implements ConstraintValidator<IdCard,String> {

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
    return IdcardUtil.isValidCard(value);
  }
}
