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
 * @ClassName: FeedbackAcceptanceEnums
 * @Author: ChnyTrcy
 * @Description: 反馈受理枚举类
 * @Date: 2022/8/25 3:39 PM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "反馈受理枚举类",type = EnumsEntityType.FEEDBACK_ACCEPTANCE)
public enum FeedbackAcceptanceEnums implements BaseEnum {
  PRODUCER_TYPE_ISOLATION(1,"隔离人员","PRODUCER_TYPE","发起用户类型"),
  PRODUCER_TYPE_EPIDEMIC_PREVENTION(2,"防疫人员","PRODUCER_TYPE","发起用户类型"),
  TYPE_MATERIAL(1,"物资请求","FEEDBACK_ACCEPTANCE_TYPE","反馈受理类型"),
  TYPE_OPINION(2,"意见申诉","FEEDBACK_ACCEPTANCE_TYPE","反馈受理类型"),
  RESULT_UNREAD(0,"未读","RESULT","处理结果"),
  RESULT_AGREE(1,"同意","RESULT","处理结果"),
  RESULT_REJECT(2,"拒绝","RESULT","处理结果"),
  RESULT_NOT_HANDLER(3,"无法处理","RESULT","处理结果"),
  IS_END_YES(1,"已结束","IS_END","是否结束"),
  IS_END_NO(0,"未结束","IS_END","是否结束"),
  ADMIN_RELATED_TAG_YES(1,"与管理员相关","ADMIN_RELATED","是否与管理员相关"),
  ADMIN_RELATED_TAG_NO(0,"与管理员无关","ADMIN_RELATED","是否与管理员相关")
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
