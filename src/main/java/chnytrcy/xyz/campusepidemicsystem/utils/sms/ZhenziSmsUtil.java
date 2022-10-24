package chnytrcy.xyz.campusepidemicsystem.utils.sms;

import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.sms
 * @ClassName: Zhenzisms
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/29 5:24 PM
 * @Version: 1.0
 */
@Component
public class ZhenziSmsUtil {

  private static final String API_URL = "https://sms_developer.zhenzikj.com";

  private static final String APP_ID = "109496";

  private static final String APP_SECRET = "3d94a71e-7dba-4567-91a3-2b611f1fdcd6";

  @Bean("ZhenziSmsClient")
  public ZhenziSmsClient zhenziSmsClient(){
    return new ZhenziSmsClient(API_URL,APP_ID,APP_SECRET);
  }

}
