package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.ProvinceMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Province;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.province.GetProvinceListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.ProvinceService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: ProvinceServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 4:51 PM
 * @Version: 1.0
 */
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements ProvinceService {

  @Override
  public Result<List<GetProvinceListVO>> getProvinceList() {
    List<Province> provinces = getBaseMapper().selectList(null);
    List<GetProvinceListVO> key = DozerUtils.convertList(provinces,
        GetProvinceListVO.class);
    return ResultFactory.successResult(key);
  }
}
