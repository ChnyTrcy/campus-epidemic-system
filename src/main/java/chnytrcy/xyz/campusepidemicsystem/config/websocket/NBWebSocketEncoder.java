package chnytrcy.xyz.campusepidemicsystem.config.websocket;

import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.websocket
 * @ClassName: NBWebSocketEncoder
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/22 11:00
 * @Version: 1.0
 */
public class NBWebSocketEncoder implements Encoder.Text<Result> {


  @Override
  public String encode(Result result) {
    return JsonUtils.writeValueAsString(result);
  }

  @Override
  public void init(EndpointConfig endpointConfig) {

  }

  @Override
  public void destroy() {

  }
}