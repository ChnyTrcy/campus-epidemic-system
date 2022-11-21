package chnytrcy.xyz.campusepidemicsystem.utils.socket;

import java.io.IOException;
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
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.socket
 * @ClassName: WebSocket
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/14 19:40
 * @Version: 1.0
 */
@ServerEndpoint(value="/app/webSocket", configurator = SpringConfigurator.class)
@Slf4j
public class WebSocket {

  private Session session;

  /**
   * 客户端连接成功
   * @param session
   */
  @OnOpen
  public void onOpen(Session session)
  {
    this.session = session;
    log.info("WebSocket - 连接成功");
  }


  /**
   * 收到消息时执行
   * @param message
   * @param session
   * @throws IOException
   */
  @OnMessage
  public void onMessage(String message, Session session) throws IOException
  {
    this.sendMessage("success");
  }


  /**
   * 关闭时执行
   */
  @OnClose
  public void onClose()
  {
    log.info("webSocket - 连接关闭");
  }


  /**
   * 连接错误时执行
   * @param session
   * @param error
   */
  @OnError
  public void onError(Session session, Throwable error)
  {
    log.error("webSocket - 出错：" + error.getMessage());
  }


  /**
   * 发送消息给客户端
   * @param message
   * @throws IOException
   */
  public void sendMessage(String message) throws IOException
  {
    this.session.getBasicRemote().sendText(message);
  }
}