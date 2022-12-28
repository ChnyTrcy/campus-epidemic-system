package xyz.chnytrcy.campusepidemicsystem.model.enums.entity;

import cn.hutool.core.util.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EnumsEntityType;
import xyz.chnytrcy.campusepidemicsystem.utils.dict.EnumKey;
import xyz.chnytrcy.core.config.basic.enums.BaseEnum;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.enums.entity
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

  STATE_NOTIFICATION_NOT_QUARANTINED(0,"通知未隔离","STATE","隔离人员状态"),
  STATE_QUARANTINED(1,"已隔离","STATE","隔离人员状态"),
  STATE_TREAT(2,"治疗中","STATE","隔离人员状态"),
  STATE_END(3,"隔离结束","STATE","隔离人员状态")
  ;
  private Integer code;

  private String desc;

  private String typeCode;

  private String type;

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
