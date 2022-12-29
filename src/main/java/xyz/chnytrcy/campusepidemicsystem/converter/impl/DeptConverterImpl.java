package xyz.chnytrcy.campusepidemicsystem.converter.impl;

import cn.hutool.core.collection.CollUtil;
import java.util.List;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Component;
import xyz.chnytrcy.campusepidemicsystem.converter.DeptConverter;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Dept;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.dept.DeptListVO;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.converter.impl
 * @ClassName: DeptConverterImpl
 * @Author: ChnyTrcy
 * @Description: 院系转换器实现类
 * @Date: 2022/12/30 01:27
 * @Version: 1.0
 */
@Component
public class DeptConverterImpl implements DeptConverter {

  @Override
  public List<DeptListVO> deptEntityToDeptListVo(List<Dept> data) {
    if(CollUtil.isEmpty(data)){
      return Lists.newArrayList();
    }
    List<DeptListVO> key = Lists.newArrayList();
    for (Dept datum : data) {
      key.add(deptToDeptListVo(datum));
    }
    return key;
  }

  private DeptListVO deptToDeptListVo(Dept data){
    DeptListVO convert = DozerUtils.convert(data, DeptListVO.class);
    convert.setLabel(data.getName());
    return convert;
  }
}
