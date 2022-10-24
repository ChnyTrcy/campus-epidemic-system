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
 * @EnumName: LeaveHealthEnums
 * @Author: ChnyTrcy
 * @Description: 请假健康枚举类
 * @Date: 2022/9/19 21:01
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "请假健康枚举类",type = EnumsEntityType.LEAVE_HEALTH)
public enum LeaveHealthEnums  implements BaseEnum {
  NUCLEIC_ACID_FEMININE(0,"阴性"),
  NUCLEIC_ACID_POSITIVE(1,"阳性")

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
