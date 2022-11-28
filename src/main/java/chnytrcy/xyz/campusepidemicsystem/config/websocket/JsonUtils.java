package chnytrcy.xyz.campusepidemicsystem.config.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.websocket
 * @ClassName: JsonUtils
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/22 11:02
 * @Version: 1.0
 */
@Slf4j
public abstract class JsonUtils {

  private static ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
  }

  /**
   * 解析JSON对象
   */
  public static <T> T parseJsonObject(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (Exception e) {
      log.error("JSON对象解析失败", e);
      throw new RuntimeException("JSON对象解析失败");
    }
  }

  /**
   * 解析JSON集合
   */
  public static <T> List<T> parseJsonList(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(
          ArrayList.class, clazz));
    } catch (Exception e) {
      log.error("JSON集合解析失败", e);
      throw new RuntimeException("JSON集合解析失败");
    }
  }

  /**
   * 解析JSON集合,class是集合类型
   * @param <T>
   * @param json
   * @param clazz
   * @return
   */
  public static <T> List<T> parseCollectionJsonList(String json, Class<T> clazz){
    try {
      return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz));
    } catch (Exception e) {
      log.error("JSON集合解析失败", e);
      throw new RuntimeException("JSON集合解析失败");
    }
  }




  /**
   * 转化为json字符串
   */
  public static String writeValueAsString(Object object){
    try{
      return objectMapper.writeValueAsString(object);
    }catch (Exception e){
      log.error("转换为JSON字符串失败", e);
      throw new RuntimeException("JSON集合解析失败");
    }
  }

}
