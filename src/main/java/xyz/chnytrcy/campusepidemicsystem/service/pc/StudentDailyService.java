package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.studentdalily.QueryStudentDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.StudentDaily;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.studentdaily.QueryStudentDailyVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: StudentDailyService
 * @Author: ChnyTrcy
 * @Description: 学生日常打卡服务接口
 * @Date: 2022/10/7 13:38
 * @Version: 1.0
 */
public interface StudentDailyService extends IService<StudentDaily> {

  /**
   * 查询学生日常打卡记录列表
   */
  Result<List<QueryStudentDailyVO>> queryStudentDaily(QueryStudentDailyCommand command);
}
