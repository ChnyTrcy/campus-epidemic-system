package xyz.chnytrcy.campusepidemicsystem.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.street.QueryStreetListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Street;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.street.QueryStreetListVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app
 * @InterfaceName: StreetAppService
 * @Author: ChnyTrcy
 * @Description: 移动端 - 街道服务接口
 * @Date: 2022/9/12 2:11 PM
 * @Version: 1.0
 */
public interface StreetAppService extends IService<Street> {

  /**
   * 查询街道列表
   */
  Result<List<QueryStreetListVO>> queryStreetList(QueryStreetListCommand command);
}
