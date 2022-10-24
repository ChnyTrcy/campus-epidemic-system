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
 * @ClassName: StudentEnums
 * @Author: ChnyTrcy
 * @Description: 学生枚举类
 * @Date: 2022/9/3 8:41 PM
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "学生枚举类",type = EnumsEntityType.STUDENT)
public enum StudentEnums implements BaseEnum {

  REPUTATION_EXCELLENT(80,"优秀"),
  REPUTATION_GOOD(55,"良好"),
  REPUTATION_GENERALLY(25,"一般"),
  REPUTATION_POOR(0,"差劲")
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

  public static String getReputationString(Integer reputation){
    if(reputation >= REPUTATION_EXCELLENT.code){
      return REPUTATION_EXCELLENT.desc;
    }else if(reputation >= REPUTATION_GOOD.code){
      return REPUTATION_GOOD.desc;
    }else if(reputation >= REPUTATION_GENERALLY.code){
      return REPUTATION_GENERALLY.desc;
    }
    return REPUTATION_POOR.desc;
  }
}
