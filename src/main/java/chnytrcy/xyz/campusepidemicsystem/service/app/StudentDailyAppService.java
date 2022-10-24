package chnytrcy.xyz.campusepidemicsystem.service.app;

import chnytrcy.xyz.campusepidemicsystem.model.command.app.studentdaily.AddStudentDailyCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.StudentDaily;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app
 * @InterfaceName: StudentDailyAppService
 * @Author: ChnyTrcy
 * @Description: 移动端 - 学生日常打卡服务接口
 * @Date: 2022/10/7 10:30
 * @Version: 1.0
 */
public interface StudentDailyAppService extends IService<StudentDaily> {

  /**
   * 增加学生日常打卡记录
   */
  Result<Void> addStudentDaily(AddStudentDailyCommand command);
}
