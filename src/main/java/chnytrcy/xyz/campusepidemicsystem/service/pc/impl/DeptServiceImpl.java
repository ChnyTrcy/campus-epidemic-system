package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.common.DeptCommon;
import chnytrcy.xyz.campusepidemicsystem.mapper.DeptMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.dept.DeptListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.DeptService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: DeptServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 8:23 PM
 * @Version: 1.0
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

  @Autowired private DeptCommon deptCommon;

  @Override
  public Result<List<DeptListVO>> getDeptList() {
    List<DeptListVO> key = Lists.newArrayList();
    deptCommon.deptList().forEach(e -> key.add(new DeptListVO(e.getCode(),e.getName())));
    return ResultFactory.successResult(key);
  }
}
