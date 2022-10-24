package chnytrcy.xyz.campusepidemicsystem.common;

import chnytrcy.xyz.campusepidemicsystem.mapper.MajorMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: MajorCommon
 * @Author: ChnyTrcy
 * @Description: 专业常见类
 * @Date: 2022/8/24 11:25 PM
 * @Version: 1.0
 */
@Component
public class MajorCommon {

  @Autowired private MajorMapper majorMapper;

  /**
   * 专业HashMap映射表
   */
  public Map<String,String> majorHashMapToName(){
    return majorMapper.selectList(null)
        .stream()
        .collect(Collectors.toMap(Major::getCode, Major::getName));
  }

}
