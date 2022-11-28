package chnytrcy.xyz.campusepidemicsystem.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.websocket
 * @ClassName: NBWebSocketService
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/22 11:01
 * @Version: 1.0
 */
@Service
@Slf4j
public class NBWebSocketService {
  //声明websocket连接类
  private NBWebSocket nbWebSocket = new NBWebSocket();

  /**
   * @param @param  message 消息
   * @param @return 发送成功返回true，否则返回false
   * @Title: sendToAllTerminal
   * @Description: 调用websocket类给用户下的所有终端发送消息
   */
  public Boolean sendToAllTerminal(String message) {
    log.debug(message);
    if (nbWebSocket.sendMessage(message)) {
      return true;
    } else {
      return false;
    }
  }

  public Boolean sendToAllTerminal(Object message) {
    log.debug(message.toString());
    if (nbWebSocket.sendMessage(message)) {
      return true;
    } else {
      return false;
    }
  }

}
