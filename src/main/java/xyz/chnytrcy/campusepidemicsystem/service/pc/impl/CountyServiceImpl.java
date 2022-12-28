package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.mapper.CountyMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.county.GetCountyListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.County;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.county.GetCountyListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.CountyService;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
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
