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

  TYPE_UP(0,"上班","TYPE","上下班状态"),
  TYPE_DOWN(1,"下班","TYPE","上下班状态"),
  RESULT_GREEN(0,"绿","RESULT","健康码颜色"),
  RESULT_YELLOW(1,"黄","RESULT","健康码颜色"),
  RESULT_RED(2,"红","RESULT","健康码颜色")
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
