package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacherdaily.QueryTeacherDailyHistoryCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.TeacherDaily;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacherdaily.QueryTeacherDailyHistoryVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
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
