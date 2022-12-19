package chnytrcy.xyz.campusepidemicsystem.config.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.socket
 * @ClassName: WebSocketConfig
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/19 22:08
 * @Version: 1.0
 */
@Configuration
public class WebSocketConfig {

  @Bean
  public ServerEndpointExporter serverEndpointExporter(){
    return new ServerEndpointExporter();
  }

}
