package chnytrcy.xyz.campusepidemicsystem.common;

import chnytrcy.xyz.campusepidemicsystem.mapper.StreetMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Street;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: StreetCommand
 * @Author: ChnyTrcy
 * @Description: 街道常用类
 * @Date: 2022/8/29 2:47 PM
 * @Version: 1.0
 */
@Component
public class StreetCommon {

  @Autowired private StreetMapper streetMapper;

  @Autowired private RedisTemplate redisTemplate;

  /**
   * 将字符串转换为List
   */
  public List<String> stringToList(String data){
    if(data == null || data.isBlank()){
      return Lists.newArrayList();
    }
    List<String> key = Lists.newArrayList();
    StringTokenizer tokenizer = new StringTokenizer(data,",");
    while(tokenizer.hasMoreTokens()){
      key.add(tokenizer.nextToken());
    }
    return key;
  }

  /**
   * 将List转换为String
   */
  public String listToString(List<String> data){
    if(data == null || data.isEmpty()){
      return "";
    }
    return data.stream().collect(Collectors.joining(","));
  }

  /**
   * 街道编号映射街道名称表
   */
  public Map<String,String> codeToString(){
    Map<String,String> streetNameHashMap = (Map<String, String>) redisTemplate.opsForValue().get("StreetNameHashMap");
    if(Objects.isNull(streetNameHashMap)){
      Map<String, String> collect = streetMapper.selectList(null)
          .stream()
          .collect(Collectors.toMap(Street::getCode, Street::getName));
      redisTemplate.opsForValue().set("StreetNameHashMap",collect);
      return collect;
    }
    return streetNameHashMap;
  }
}
