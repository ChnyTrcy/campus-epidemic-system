package xyz.chnytrcy.campusepidemicsystem.config.socket.component;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.config.socket.component
 * @ClassName: WebSocketServer
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/19 22:09
 * @Version: 1.0
 */
@ServerEndpoint(value = "/chat/{userId}")
@Slf4j
public class WebSocketServer {

  /**
   * 记录当前在线连接数
   */
  public static final Map<String, Session> SESSION_MAP = new ConcurrentHashMap<>();

  /**
   * 连接成功调用的方法
   */
  @OnOpen
  public void onOpen(Session session,@PathParam("userId") String userId){
    SESSION_MAP.put(userId,session);
    log.info("有新用户加入，userId = {},当前在线人数为：{}",userId,SESSION_MAP.size());
    JSONObject result = new JSONObject();
    JSONArray array = new JSONArray();
    result.put("users",array);
    for (String aLong : SESSION_MAP.keySet()) {
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("userId",aLong);
      array.add(jsonObject);
    }
    //发送消息给所有的客户端
    this.sendAllMessage(JSON.toJSONString(result));
  }

  @OnClose
  public void onClose(Session session,@PathParam("userId") String userId){
    SESSION_MAP.remove(userId);
    log.info("有一个连接关闭，移除userId = {}的用户session，当前在线人数为：{}",userId,SESSION_MAP.size());
  }

  /**
   * 收到客户端消息后调用的方法
   * 后台收到客户端发送过来的消息
   * onMessage 是一个消息的中转站
   * 接受 浏览器端 socket.send 发送过来的 json数据
   */
  @OnMessage
  public void onMessage(String message,Session session,@PathParam("userId") String userId){
    log.info("服务端收到用户userId = {}的消息：{}",userId,message);
    JSONObject obj = (JSONObject) JSON.parse(message);
    String toUserId = obj.getString("to");
    String text = obj.getString("text");
    Session toSession = SESSION_MAP.get(toUserId);
    if(ObjectUtil.isNotNull(toSession)){
      //服务器重新组装消息体
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("from",userId);
      jsonObject.put("text",text);
      this.sendMessage(JSON.toJSONString(jsonObject),toSession);
      log.info("发送给用户userId = {}，消息：{}",toUserId,JSON.toJSONString(jsonObject));
    }else {
      log.warn("发送失败，未找到用户userId = {}的session",toUserId);
    }
  }

  @OnError
  public void onError(Session session,Throwable error){
    log.error("发生错误");
    error.printStackTrace();
  }

  /**
   * 服务端发送消息给客户端
   * @param message
   * @param toSession
   */
  private void sendMessage(String message,Session toSession){
    log.info("服务端给客户端[{}]发送消息{}",toSession.getId(),message);
    try {
      toSession.getBasicRemote().sendText(message);
    } catch (IOException e) {
      log.error("服务端发送消息失败",e);
    }
  }

  /**
   * 服务端发送消息给所有客户端
   */
  private void sendAllMessage(String message){
    try {
      for (Session session : SESSION_MAP.values()) {
        log.info("服务端给客户端[{}]发送消息{}",session.getId(),message);
        session.getBasicRemote().sendText(message);
      }
    } catch (IOException e) {
      log.error("服务端发送消息给客户端失败",e);
    }
  }
}
