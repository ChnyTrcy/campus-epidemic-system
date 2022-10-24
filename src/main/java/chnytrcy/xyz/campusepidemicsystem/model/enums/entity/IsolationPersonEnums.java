package chnytrcy.xyz.campusepidemicsystem.model.enums.entity;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.EnumKey;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseEnum;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EnumsEntityType;
import cn.hutool.core.util.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.enums.entity
 * @EnumName: IsolationPersonEmuns
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/25 3:52 PM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "隔离人员枚举类",type = EnumsEntityType.ISOLATION_PERSON)
public enum IsolationPersonEnums implements BaseEnum {

  STATE_NOTIFICATION_NOT_QUARANTINED(0,"通知未隔离"),
  STATE_QUARANTINED(1,"已隔离"),
  STATE_TREAT(2,"治疗中"),
  STATE_END(3,"隔离结束")
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
