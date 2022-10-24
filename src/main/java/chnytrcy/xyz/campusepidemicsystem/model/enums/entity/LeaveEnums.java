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
 * @EnumName: LeaveEnums
 * @Author: ChnyTrcy
 * @Description: 日常出入校离校枚举类
 * @Date: 2022/8/25 4:02 PM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "日常出入校离校枚举类",type = EnumsEntityType.LEAVE)
public enum LeaveEnums implements BaseEnum {
  TYPE_PERSONAL(0,"事假"),
  TYPE_SICK(1,"病假"),
  APPROVAL_RESULT_NOT_PROCESSED(0,"未处理"),
  APPROVAL_RESULT_AGREE(1,"同意"),
  APPROVAL_RESULT_REJECT(2,"拒绝"),
  APPROVAL_RESULT_DEAL(3,"还在处理"),
  IS_START_YES(1,"已经出校"),
  IS_START_NO(0,"未出校"),
  IS_RETURN_YES(1,"已经返校"),
  IS_RETURN_NO(0,"未返校"),
  IS_STRIDE_COUNTY_YES(1,"跨区"),
  IS_STRIDE_COUNTY_NO(0,"未跨区"),
  IS_STRIDE_DAY_YES(1,"跨天"),
  IS_STRIDE_DAY_NO(0,"未跨天"),
  IS_OVERDUE_YES(1,"已过期"),
  IS_OVERDUE_NO(0,"未过期"),

  IS_END_YES(1,"审批结束"),
  IS_END_NO(0,"审批未结束")

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
