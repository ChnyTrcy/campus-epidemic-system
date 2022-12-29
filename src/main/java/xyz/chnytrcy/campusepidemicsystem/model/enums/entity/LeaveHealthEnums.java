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
  NUCLEIC_ACID_FEMININE(0,"阴性","NUCLEIC_ACID","核酸结果"),
  NUCLEIC_ACID_POSITIVE(1,"阳性","NUCLEIC_ACID","核酸结果")

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
