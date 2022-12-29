package xyz.chnytrcy.campusepidemicsystem.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.studentdaily.AddStudentDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.StudentDaily;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app
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
