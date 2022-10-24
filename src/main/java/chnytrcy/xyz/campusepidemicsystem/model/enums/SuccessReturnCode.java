package chnytrcy.xyz.campusepidemicsystem.model.enums;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.EnumKey;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseEnum;
import cn.hutool.core.util.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 成功返回参数
 * @author chnytrcy
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "成功返回参数",type = EnumsEntityType.SUCCESS_RETURN)
public enum SuccessReturnCode implements BaseEnum {
  NORMAL(200,"success"),
  LOGOUT(300,"用户登出成功"),

  CHANGE_PASSWORD(301,"密码修改成功")
  ;

  private Integer code;

  private String desc;

  @Override
  public Integer getNumber() {
    return getCode();
  }

  @Override
  public String getName() {
    return getDesc();
  }

  @Override
  public String getSimplePinYin() {
    return PinyinUtil.getAllFirstLetter(getDesc());
  }

  @Override
  public String getFullPinYin() {
    return PinyinUtil.getPinYin(getDesc());
  }
}
