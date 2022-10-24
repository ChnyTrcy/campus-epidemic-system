package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.entity.Province;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.province.GetProvinceListVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
 * @InterfaceName: ProvinceService
 * @Author: ChnyTrcy
 * @Description: 省份服务接口
 * @Date: 2022/8/24 4:50 PM
 * @Version: 1.0
 */
public interface ProvinceService extends IService<Province> {

  /**
   * 获取省份列表
   */
  Result<List<GetProvinceListVO>> getProvinceList();
}
