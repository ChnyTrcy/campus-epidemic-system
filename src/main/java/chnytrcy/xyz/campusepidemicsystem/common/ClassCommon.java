package chnytrcy.xyz.campusepidemicsystem.common;

import chnytrcy.xyz.campusepidemicsystem.mapper.ClassMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: ClassCommonUtil
 * @Author: ChnyTrcy
 * @Description: 班级常见工具类
 * @Date: 2022/8/24 5:50 PM
 * @Version: 1.0
 */
@Component
public class ClassCommon {

  @Autowired
  private ClassMapper classMapper;

  public Map<String,String> classHashMapToName(){
    return classMapper.selectList(null)
        .stream()
        .collect(Collectors.toMap(ClassEntity::getCode, ClassEntity::getName));
  }
}
