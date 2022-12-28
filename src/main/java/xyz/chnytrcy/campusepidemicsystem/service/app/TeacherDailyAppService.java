package xyz.chnytrcy.campusepidemicsystem.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.teacherdaily.DownDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.teacherdaily.UpDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.TeacherDaily;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app
 * @InterfaceName: TeacherDailyAppService
 * @Author: ChnyTrcy
 * @Description: 教职工打卡移动端服务接口
 * @Date: 2022/9/6 11:20 AM
 * @Version: 1.0
 */
public interface TeacherDailyAppService extends IService<TeacherDaily> {

  /**
   * 教职工/防疫人员上班打卡
   */
  Result<Void> upDaily(UpDailyCommand command);

  /**
   * 教职工/防疫人员下班打卡
   */
  Result<Void> downDaily(DownDailyCommand command);
}
