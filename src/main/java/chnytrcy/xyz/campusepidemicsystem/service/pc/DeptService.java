package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.dept.DeptListVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
 * @InterfaceName: DeptService
 * @Author: ChnyTrcy
 * @Description: 院系服务接口
 * @Date: 2022/8/24 8:23 PM
 * @Version: 1.0
 */
public interface DeptService extends IService<Dept> {

  /**
   * 获取院系列表
   */
  Result<List<DeptListVO>> getDeptList();
}
