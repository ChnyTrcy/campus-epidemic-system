package xyz.chnytrcy.campusepidemicsystem.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.teacher.GetInformationVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app
 * @InterfaceName: TeacherAppService
 * @Author: ChnyTrcy
 * @Description: 移动端 - 教职工服务接口
 * @Date: 2022/9/13 5:32 PM
 * @Version: 1.0
 */
public interface TeacherAppService extends IService<Teacher> {

  /**
   * 获得教职工/防疫人员信息
   */
  Result<GetInformationVO> getInformation();
}
