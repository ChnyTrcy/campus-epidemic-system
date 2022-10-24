package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.CountyMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.county.GetCountyListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.County;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.county.GetCountyListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.CountyService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: CountyServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 3:29 PM
 * @Version: 1.0
 */
@Service
public class CountyServiceImpl extends ServiceImpl<CountyMapper, County> implements CountyService {

  @Override
  public Result<List<GetCountyListVO>> getCountyList(GetCountyListCommand command) {
    List<County> counties = getBaseMapper().selectList(new LambdaQueryWrapper<County>()
        .eq(County::getCityCode, command.getCityCode()));
    List<GetCountyListVO> key = DozerUtils.convertList(counties,
        GetCountyListVO.class);
    return ResultFactory.successResult(key);
  }
}
