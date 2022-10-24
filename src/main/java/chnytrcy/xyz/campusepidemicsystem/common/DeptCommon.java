package chnytrcy.xyz.campusepidemicsystem.common;

import chnytrcy.xyz.campusepidemicsystem.mapper.DeptMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.common
 * @ClassName: DeptCommon
 * @Author: ChnyTrcy
 * @Description: 院系常见类
 * @Date: 2022/8/24 8:31 PM
 * @Version: 1.0
 */
@Component
public class DeptCommon {

  @Autowired
  private DeptMapper deptMapper;

  /**
   * 获取院系编号与名字映射表
   */
  public Map<String,String> deptHashMap(){
    return deptMapper.selectList(null)
            .stream()
            .collect(Collectors.toMap(Dept::getCode, Dept::getName));
  }

  public List<Dept> deptList(){
    return deptMapper.selectList(
        new LambdaQueryWrapper<Dept>().select(Dept::getCode,Dept::getName));
  }

}
