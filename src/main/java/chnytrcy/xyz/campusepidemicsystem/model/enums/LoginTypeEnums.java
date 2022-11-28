package chnytrcy.xyz.campusepidemicsystem.model.enums;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.EnumKey;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseEnum;
import cn.hutool.core.util.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.enums
 * @EnumName: LoginTypeEnums
 * @Author: ChnyTrcy
 * @Description: 登陆方式枚举类
 * @Date: 2022/11/23 14:32
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "登陆类型",type = EnumsEntityType.LOGIN)
public enum LoginTypeEnums implements BaseEnum {
  PC_PASSWORD(1,"PC端账号密码登陆"),
  PC_PHONE(2,"PC端手机验证码登陆"),
  APP_PASSWORD(3,"App端账号密码登陆")

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
