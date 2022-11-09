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
 * @ClassName: TeacherEnums
 * @Author: ChnyTrcy
 * @Description: 教职工枚举类
 * @Date: 2022/8/28 3:38 PM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "教职工枚举类",type = EnumsEntityType.TEACHER)
public enum TeacherEnums implements BaseEnum {
  EPIDEMIC_MARK_YES(1,"是否为防疫人员：是","EPIDEMIC_MARK","是否为防疫人员"),
  EPIDEMIC_MARK_NO(0,"是否为防疫人员：否","EPIDEMIC_MARK","是否为防疫人员"),

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
