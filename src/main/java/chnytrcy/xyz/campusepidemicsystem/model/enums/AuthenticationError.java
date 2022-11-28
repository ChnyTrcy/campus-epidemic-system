package chnytrcy.xyz.campusepidemicsystem.model.enums;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.EnumKey;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseEnum;
import cn.hutool.core.util.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * @Description: 错误类型枚举
 * @Author: ChnyTrcy
 * @Date: 2022/8/20 4:13 PM
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "认证错误返回参数",type = EnumsEntityType.AUTHENTICATION_ERROR)
public enum AuthenticationError implements BaseEnum {
  LOGIN_ROLE_ERROR(1001,"对不起，您没有对应的角色,拒绝访问"),
  LOGON_PERMISSION_MISSING_ERROR(1002,"对不起，您没有对应的权限,拒绝访问"),
  LOGIN_PERMISSION_ERROR(1003,"对不起，您的权限有误，拒绝访问"),
  LOGOUT_ERROR(1004,"用户登出失败"),
  LOGIN_ACCOUNT_NOT_EXIST_ERROR(1005,"账户不存在"),
  LOGIN_PASSWORD_ERROR(1006,"密码输入错误，请重新输入"),
  LOGIN_AUTHORIZATION_INVALID_ERROR(1007,"登录凭证已失效，请重新登录"),
  LOGIN_UNAUTHORIZED_ACCESS_ERROR(1008,"非法访问，请重新登录"),
  PASSWORD_NOT_SAME_ERROR(1009,"两次密码不相同"),
  PASSWORD_SAME_WITH_OLD_ERROR(1010,"密码与原始密码相同"),
  ROLE_UNKNOWN_ERROR(1011,"未知的用户类型"),
  PC_UNALLOW_TYPE_ERROR(1012,"PC端不可用的登陆方式"),
  EPIDEMIC_UNKNOWN_INFORMATION_ERROR(1013,"查询不到防疫人员基本信息"),
  ADMIN_UNKNOWN_INFORMATION_ERROR(1014,"查询不到管理员基本信息"),
  MOBILE_UNKNOWN_TYPE_ERROR(1015,"App端不可用的登陆方式"),
  STUDENT_UNKNOWN_INFORMATION_ERROR(1016,"查询不到学生（隔离人员）基本信息"),
  CAPTCHA_GET_ERROR(1017,"验证码获取失败"),
  CAPTCHA_TIME_ERROR(1018,"验证码失效"),
  CAPTCHA_DIFFERENT_ERROR(1019,"验证码错误"),
  SMS_MESSAGE_ERROR(1020,"短息发送错误，请重试"),
  SMS_MESSAGE_EFFECTIVE_ERROR(1021,"短信仍在有效期内，请勿重复发送"),
  SMS_SWITCH_CLOSE_ERROR(1022,"未开启短信登陆，请联系管理员"),
  SMS_MESSAGE_NULL_ERROR(1023,"未发送短信，请获取短信"),
  SMS_CODE_ERROR(1024,"短信验证码错误"),
  SMS_TIME_LONG_ERROR(1025,"短信失效，请重新获取短信"),
  LOGIN_ASSOCIATED_PHONE_MORE_ERROR(1026,"手机关联的账号过多，请联系管理员")
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
