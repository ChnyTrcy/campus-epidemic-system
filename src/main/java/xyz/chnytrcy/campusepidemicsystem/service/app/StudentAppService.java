package xyz.chnytrcy.campusepidemicsystem.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.student.ScanCodeGetStudentInformationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.student.GetUserIdVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.student.ScanCodeGetStudentInformationVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app
 * @InterfaceName: StudentAppService
 * @Author: ChnyTrcy
 * @Description: 移动端 - 学生服务接口
 * @Date: 2022/9/12 2:44 PM
 * @Version: 1.0
 */
public interface StudentAppService extends IService<Student> {

  /**
   * 获取学生ID
   */
  Result<GetUserIdVO> getUserId();

  /**
   * 扫码获取学生的信息以及操作权限
   */
  Result<ScanCodeGetStudentInformationVO> scanCodeGetStudentInformation(
      ScanCodeGetStudentInformationCommand command);
}
