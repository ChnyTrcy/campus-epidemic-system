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

  RISK_LEVEL_HIGH(2,"高风险"),
  RISK_LEVEL_MIDDLE(1,"中风险"),
  RISK_LEVEL_LOW(0,"低风险")
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
