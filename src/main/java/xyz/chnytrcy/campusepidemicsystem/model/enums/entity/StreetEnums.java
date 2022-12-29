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
 * @ClassName: StreetEnums
 * @Author: ChnyTrcy
 * @Description: 街道枚举类
 * @Date: 2022/9/1 9:05 AM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "街道枚举类",type = EnumsEntityType.STREET)
public enum StreetEnums implements BaseEnum {

  RISK_LEVEL_HIGH(2,"高风险","RISK_LEVEL","风险等级"),
  RISK_LEVEL_MIDDLE(1,"中风险","RISK_LEVEL","风险等级"),
  RISK_LEVEL_LOW(0,"低风险","RISK_LEVEL","风险等级")
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
