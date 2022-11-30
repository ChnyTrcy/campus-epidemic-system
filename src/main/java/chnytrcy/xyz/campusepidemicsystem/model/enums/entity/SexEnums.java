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
 * @EnumName: SexEnums
 * @Author: ChnyTrcy
 * @Description: 性别枚举类
 * @Date: 2022/11/30 15:45
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "日常出入校离校枚举类",type = EnumsEntityType.SEX)
public enum SexEnums implements BaseEnum {
  MAN(1,"男","SEX","性别"),
  WOMAN(2,"女","SEX","性别")
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
