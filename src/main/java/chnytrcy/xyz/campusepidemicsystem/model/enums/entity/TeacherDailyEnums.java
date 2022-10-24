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
 * @EnumName: TeacherDailyEnums
 * @Author: ChnyTrcy
 * @Description: 教职工日常打卡枚举类
 * @Date: 2022/9/6 2:36 PM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "教职工上下班枚举类",type = EnumsEntityType.TEACHER_DAILY)
public enum TeacherDailyEnums implements BaseEnum {

  TYPE_UP(0,"上班"),
  TYPE_DOWN(1,"下班"),
  RESULT_GREEN(0,"绿"),
  RESULT_YELLOW(1,"黄"),
  RESULT_RED(2,"红")
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
