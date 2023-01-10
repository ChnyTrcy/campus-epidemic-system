package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.mapper.ProvinceMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Province;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.province.GetProvinceListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.ProvinceService;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
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
