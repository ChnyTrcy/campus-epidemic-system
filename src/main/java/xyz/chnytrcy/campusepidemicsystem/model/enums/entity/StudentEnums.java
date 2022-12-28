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

  REPUTATION_EXCELLENT(80,"优秀","REPUTATION","信誉分"),
  REPUTATION_GOOD(55,"良好","REPUTATION","信誉分"),
  REPUTATION_GENERALLY(25,"一般","REPUTATION","信誉分"),
  REPUTATION_POOR(0,"差劲","REPUTATION","信誉分")
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
