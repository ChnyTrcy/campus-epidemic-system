package xyz.chnytrcy.campusepidemicsystem.utils.socket;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.socket
 * @ClassName: WebSocketUtil
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/30 20:16
 * @Version: 1.0
 */
public class WebSocketUtil {
  /**
   * 记录当前在线的Session
   */
  private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();

  /**
   * 消息仓库，未消费的消息体
   */
  private static final Map<String,List<JSONObject>> MESSAGE_WAREHOUSE = new ConcurrentHashMap<>();

  /**
   * 添加session
   * @param userId
   * @param session
   */
  public static void addSession(String userId, Session session){
    // 此处只允许一个用户的session链接。一个用户的多个连接，我们视为无效。
    ONLINE_SESSION.putIfAbsent( userId, session );
  }

  /**
   * 关闭session
   * @param userId
   */
  public static void removeSession(String userId){
    ONLINE_SESSION.remove( userId );
  }

  /**
   * 给单个用户推送消息
   * @param session
   * @param message
   */
  public static void sendMessage(Session session, String message) throws IOException {
    if(session == null){
      return;
    }
    session.getBasicRemote().sendText(message);
  }

  /**
   * 向所有在线人发送消息
   * @param message
   */
//  public static void sendMessageForAll(String message) {
//    ONLINE_SESSION.forEach((sessionId, session) -> sendMessage(session, message));
//  }

  public static void sendMessage(String sourceId,String targetId,String message,Session session)
      throws IOException {
    Session session1 = ONLINE_SESSION.get(targetId);
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("sourceId",sourceId);
    jsonObject.put("message",message);
    jsonObject.put("time",LocalDateTime.now());
    if(session1 == null){
      //表示该用户没有上线，那么则放入消息队列
      List<JSONObject> orDefault = MESSAGE_WAREHOUSE.getOrDefault(targetId, new ArrayList<>());
      orDefault.add(jsonObject);
      MESSAGE_WAREHOUSE.put(targetId,orDefault);
    }else {
      //反组装信息体
      String s = jsonObject.toJSONString();
      sendMessage(session1,s);
    }
    //数据库实例化,异步操作

  }

  /**
   * 用户刚初始化连接websocket的时候，消费堆积的信息
   * @param userId
   */
  public static void initMessageQueue(String userId){
    List<JSONObject> jsonObjects = MESSAGE_WAREHOUSE.get(userId);
    Session session = ONLINE_SESSION.get(userId);
    if(!CollectionUtil.isEmpty(jsonObjects)){
      jsonObjects.forEach(e -> {
        try {
          sendMessage(session,e.toJSONString());
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      });
      MESSAGE_WAREHOUSE.remove(userId);
    }
  }
}
