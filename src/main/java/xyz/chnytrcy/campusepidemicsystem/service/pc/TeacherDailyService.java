package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.teacherdaily.QueryTeacherDailyHistoryCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.TeacherDaily;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.teacherdaily.QueryTeacherDailyHistoryVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: TeacherDailyService
 * @Author: ChnyTrcy
 * @Description: 教职工日常打卡服务接口
 * @Date: 2022/8/24 5:02 PM
 * @Version: 1.0
 */
public interface TeacherDailyService extends IService<TeacherDaily> {

  /**
   * 查看10天该教职工的历史打卡记录
   */
  Result<List<QueryTeacherDailyHistoryVO>> queryTeacherDailyHistory(
      QueryTeacherDailyHistoryCommand command);
}
