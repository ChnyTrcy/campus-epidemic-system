package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Province;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.province.GetProvinceListVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
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
