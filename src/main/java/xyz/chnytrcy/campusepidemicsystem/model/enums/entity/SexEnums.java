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
