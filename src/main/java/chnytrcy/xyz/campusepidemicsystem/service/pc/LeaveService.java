package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.AgreeLeaveCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.QueryPageLeaveListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.RejectLeaveCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave.AnalyzeAdminLeaveVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave.QueryPageLeaveListVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
 * @InterfaceName: LeaveService
 * @Author: ChnyTrcy
 * @Description: 日常出入校请假服务接口
 * @Date: 2022/8/24 4:45 PM
 * @Version: 1.0
 */
public interface LeaveService extends IService<Leave> {

  /**
   * 查看本院系的请假列表
   */
  Result<BasePageVO<QueryPageLeaveListVO>> queryPageLeaveList(QueryPageLeaveListCommand command);

  /**
   * 同意请假申请
   */
  Result<Void> agreeLeave(AgreeLeaveCommand command);

  /**
   * 拒绝请假申请
   */
  Result<Void> rejectLeave(RejectLeaveCommand command);

  /**
   * 统计分析请假数
   */
  Result<AnalyzeAdminLeaveVO> analyzeAdminLeave();
}
