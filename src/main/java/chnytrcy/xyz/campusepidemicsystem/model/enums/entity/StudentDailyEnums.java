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
 * @EnumName: StudentDailyEnums
 * @Author: ChnyTrcy
 * @Description: 学生日常打卡枚举类
 * @Date: 2022/10/7 12:35
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "学生日常打卡枚举类",type = EnumsEntityType.STUDENT_DAILY)
public enum StudentDailyEnums  implements BaseEnum {

  TEMPERATURE_ABNORMAL_NORMAL(0,"体温正常","TEMPERATURE_ABNORMAL","体温是否异常"),
  TEMPERATURE_ABNORMAL_ABNORMAL(1,"体温异常","TEMPERATURE_ABNORMAL","体温是否异常"),
  ABNORMAL_SYMPTOMS_NORMAL(0,"无异常症状","ABNORMAL_SYMPTOMS","是否有异常症状"),
  ABNORMAL_SYMPTOMS_ABNORMAL(1,"有异常症状","ABNORMAL_SYMPTOMS","是否有异常症状"),
  HEALTH_CODE_GREEN(0,"健康码-绿","HEALTH_CODE","健康码颜色"),
  HEALTH_CODE_YELLOW(1,"健康码-黄","HEALTH_CODE","健康码颜色"),
  HEALTH_CODE_RED(2,"健康码-红","HEALTH_CODE","健康码颜色"),
  HEALTH_CODE_NULL(3,"健康码未上传","HEALTH_CODE","健康码颜色"),
  PROMISE_YES(0,"承诺-接受","PROMISE","承诺结果"),
  PROMISE_NO(1,"承诺-拒绝","PROMISE","承诺结果")

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
