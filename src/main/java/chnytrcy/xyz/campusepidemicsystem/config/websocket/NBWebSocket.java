package chnytrcy.xyz.campusepidemicsystem.config.websocket;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.server.standard.SpringConfigurator;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.websocket
 * @ClassName: NBWebSocket
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/22 10:58
 * @Version: 1.0
 */
@ServerEndpoint(value = "/websocket", configurator = SpringConfigurator.class, encoders = NBWebSocketEncoder.class)
@Slf4j
public class NBWebSocket {

  //记录每个用户下多个终端的连接
  private static Set<Session> userSocket = new HashSet<>();

  //需要session来对用户发送数据, 获取连接特征userId
  private Session session;

  /**
   * @param @param  session websocket连接的session属性
   * @param @throws IOException
   * @Title: onOpen
   * @Description: websocekt连接建立时的操作
   */
  @OnOpen
  public void onOpen(Session session) throws IOException {
    this.session = session;
    userSocket.add(this.session);
    log.debug("webSocket open");
  }

  /**
   * @Title: onClose
   * @Description: 连接关闭的操作
   */
  @OnClose
  public void onClose() {
    userSocket.remove(this.session);
    log.debug("webSocket closed!");
  }

  /**
   * @param @param message 收到的消息
   * @param @param session 该连接的session属性
   * @Title: onMessage
   * @Description: 收到消息后的操作
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    log.debug("收到消息" + message);
    if (session == null) log.debug("session null");
  }

  /**
   * @param session 该连接的session
   * @param error 发生的错误
   * @Title: onError
   * @Description: 连接发生错误时候的操作
   */
  @OnError
  public void onError(Session session, Throwable error) {
    log.debug("连接发送错误");
    error.printStackTrace();
  }

  /**
   * @param message 发送的消息
   * @param @return 发送成功返回true，反则返回false
   * @Title: sendMessage
   * @Description: 发送消息给用户下的所有终端
   */
  public Boolean sendMessage(String message) {
    userSocket.stream().forEach(ws -> {
      try {
        ws.getBasicRemote().sendText(message);

      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    return true;
  }

  public Boolean sendMessage(Object message) {
    userSocket.stream().forEach(ws -> {
      try {
        ws.getBasicRemote().sendObject(message);
      } catch (IOException | EncodeException e) {
        e.printStackTrace();
      }
    });
    return true;
  }
}
