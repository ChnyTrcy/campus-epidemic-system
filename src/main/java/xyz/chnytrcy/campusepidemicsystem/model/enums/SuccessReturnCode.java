package xyz.chnytrcy.campusepidemicsystem.model.enums;

import cn.hutool.core.util.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.chnytrcy.campusepidemicsystem.utils.dict.EnumKey;
import xyz.chnytrcy.core.config.basic.enums.BaseEnum;

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

  WARING(201,"存在异常"),

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
