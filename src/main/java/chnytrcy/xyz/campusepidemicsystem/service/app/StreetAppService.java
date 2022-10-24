package chnytrcy.xyz.campusepidemicsystem.service.app;

import chnytrcy.xyz.campusepidemicsystem.model.command.app.street.QueryStreetListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Street;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.street.QueryStreetListVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app
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
