package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.DealReturnLeaveCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.LeaveDetail;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
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
