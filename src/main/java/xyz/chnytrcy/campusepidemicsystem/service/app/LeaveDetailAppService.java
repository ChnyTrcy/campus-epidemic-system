package xyz.chnytrcy.campusepidemicsystem.service.app;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.leave.ReturnLeaveToAdminMessageCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.LeaveDetail;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app
 * @InterfaceName: LeaveDetailAppService
 * @Author: ChnyTrcy
 * @Description: 移动端 - 离校过程服务接口
 * @Date: 2022/9/8 12:21 AM
 * @Version: 1.0
 */

public interface LeaveDetailAppService extends IService<LeaveDetail> {

  /**
   * 学生回复管理员请假理由
   */
  Result<Void> returnLeaveToAdminMessage(ReturnLeaveToAdminMessageCommand command);
}
