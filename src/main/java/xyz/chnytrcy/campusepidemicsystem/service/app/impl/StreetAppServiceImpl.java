package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.mapper.StreetMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.street.QueryStreetListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Street;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.street.QueryStreetListVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.StreetAppService;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
 * @ClassName: StreetAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/12 2:11 PM
 * @Version: 1.0
 */
@Service
public class StreetAppServiceImpl extends ServiceImpl<StreetMapper, Street>
    implements StreetAppService {

  @Override
  public Result<List<QueryStreetListVO>> queryStreetList(QueryStreetListCommand command) {
    List<Street> streets = getBaseMapper().selectList(
        new LambdaQueryWrapper<Street>()
            .eq(Street::getCountyCode, command.getCode()));
    List<QueryStreetListVO> vos = DozerUtils.convertList(streets,
        QueryStreetListVO.class);
    return ResultFactory.successResult(vos);
  }
}
