package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.DealReturnLeaveCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.LeaveDetail;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: LeabeDetailService
 * @Author: ChnyTrcy
 * @Description: 离校过程服务接口
 * @Date: 2022/9/7 9:23 PM
 * @Version: 1.0
 */
public interface LeaveDetailService extends IService<LeaveDetail> {

  /**
   * 管理员回复请假申请
   */
  Result<Void> dealReturnLeave(DealReturnLeaveCommand command);
}
