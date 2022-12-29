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
 * @EnumName: UserRoleEnums
 * @Author: ChnyTrcy
 * @Description: 角色枚举
 * @Date: 2022/8/31 2:45 PM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "角色枚举类",type = EnumsEntityType.ROLE)
public enum RoleEnums implements BaseEnum {
  ADMIN(1,"管理员","TYPE","用户类型"),
  TEACHER(2,"教职工","TYPE","用户类型"),
  EPIDEMIC_PREVENTION(3,"防疫人员","TYPE","用户类型"),
  STUDENT(4,"学生","TYPE","用户类型"),
  QUARANTINE(5,"隔离人员","TYPE","用户类型")
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
