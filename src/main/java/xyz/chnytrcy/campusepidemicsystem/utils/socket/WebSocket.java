package xyz.chnytrcy.campusepidemicsystem.utils.socket;

import java.io.IOException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.config.socket
 * @ClassName: WebSocket
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/14 19:40
 * @Version: 1.0
 */
@ServerEndpoint(value="/campus-epidemic-system/webSocket/", configurator = SpringConfigurator.class)
@Component
@Slf4j
public class WebSocket {

  /**
   * 连接事件，加入注解
   * @param userId
   * @param session
   */
  @OnOpen
  public void onOpen( @PathParam( value = "userid" ) String userId, Session session ) {
    String message ="[" + userId + "]加入聊天室！！";

    // 添加到session的映射关系中
    WebSocketUtil.addSession ( userId, session );
    // 广播通知，某用户上线了
    WebSocketUtil.sendMessageForAll ( message );
  }

  /**
   * 连接事件，加入注解
   * 用户断开链接
   * @param userId
   * @param session
   */
  @OnClose
  public void onClose(@PathParam ( value = "userId" ) String userId, Session session ) {
    String message ="[" + userId + "]退出了聊天室...";

    // 删除映射关系
    WebSocketUtil.removeSession ( userId );
    // 广播通知，用户下线了
    WebSocketUtil.sendMessageForAll ( message );
  }

  /**
   * 当接收到用户上传的消息
   * @param userId
   * @param session
   */
  @OnMessage
  public void onMessage(@PathParam ( value = "userId" ) String userId, Session session ,String message) {
    String msg ="[" + userId + "]:"+message;

    // 直接广播
    WebSocketUtil.sendMessageForAll ( msg );
  }

  /**
   * 处理用户活连接异常
   * @param session
   * @param throwable
   */
  @OnError
  public void onError(Session session, Throwable throwable) {
    try {
      session.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    throwable.printStackTrace();
  }
}