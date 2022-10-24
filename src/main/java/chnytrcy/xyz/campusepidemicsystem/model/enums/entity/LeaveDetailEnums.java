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
 * @EnumName: LeaveDetailEnums
 * @Author: ChnyTrcy
 * @Description: 离校申请枚举类
 * @Date: 2022/9/7 10:30 PM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "离校申请枚举类",type = EnumsEntityType.LEAVE_DETAIL)
public enum LeaveDetailEnums implements BaseEnum {

  TYPE_STUDENT(1,"学生提出"),
  TYPE_ADMIN_REPEAT(2,"管理员回复"),
  TYPE_STUDENT_AGAIN(3,"学生再次回复"),
  TYPE_END(4,"管理员终审")
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
