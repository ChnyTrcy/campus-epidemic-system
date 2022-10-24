package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.StreetMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.street.QueryStreetListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Street;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.street.QueryStreetListVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.StreetAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
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
