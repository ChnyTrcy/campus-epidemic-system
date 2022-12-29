package xyz.chnytrcy.campusepidemicsystem.model.constance;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.constance
 * @InterfaceName: RedisPrefixConstance
 * @Author: ChnyTrcy
 * @Description: Redis前缀
 * @Date: 2022/11/22 14:54
 * @Version: 1.0
 */
public interface RedisPrefixConstance {
  String LOGIN_TOKEN_PREFIX = "LOGIN-TOKEN-";
  String LOGIN_CAPTCHA_PICTURE_PREFIX = "LOGIN-CAPTCHA-PICTURE-";
  String LOGIN_CAPTCHA_SMS_PREFIX = "LOGIN-CAPTCHA-SMS-";
}
