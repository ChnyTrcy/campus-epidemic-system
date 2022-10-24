package chnytrcy.xyz.campusepidemicsystem.utils.dozer;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: DozerUtil
 * @Author: ChnyTrcy
 * @Description: 类型转换器
 * @Date: 2022/8/24 7:58 PM
 * @Version: 1.0
 */
public class DozerUtils {
  public static final Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();
  static ObjectMapper objectMapper = new ObjectMapper();

  public DozerUtils() {
  }

  public static <T> T convert(Object source, Class<T> destClass) {
    return source == null ? null : MAPPER.map(source, destClass);
  }

  public static <T, U> List<U> convertList(List<T> source, Class<U> destClass) {
    if (source == null) {
      return null;
    } else {
      List<U> list = new ArrayList();
      source.forEach((item) -> {
        list.add(MAPPER.map(item, destClass));
      });
      return list;
    }
  }

  public static <T> BasePageVO parse(BasePageVO pageVO, Class<T> uClass) {
    if (pageVO != null && uClass != null) {
      List<Object> tList = pageVO.getData();
      if (tList == null) {
        return null;
      } else {
        List uList = convertList(tList, uClass);
        BasePageVO uPageVO = new BasePageVO();
        uPageVO.setCurPage(pageVO.getCurPage());
        uPageVO.setSize(pageVO.getSize());
        uPageVO.setTotal(pageVO.getTotal());
        uPageVO.setTotalPage(pageVO.getTotalPage());
        uPageVO.setData(uList);
        return uPageVO;
      }
    } else {
      return null;
    }
  }


  static {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
  }
}
