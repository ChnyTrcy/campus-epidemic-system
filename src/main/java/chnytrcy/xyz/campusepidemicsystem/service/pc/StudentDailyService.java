package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.studentdalily.QueryStudentDailyCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.StudentDaily;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.studentdaily.QueryStudentDailyVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
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
