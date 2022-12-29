package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.AgreeLeaveCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.QueryPageLeaveListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.RejectLeaveCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Leave;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.leave.AnalyzeAdminLeaveVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.leave.QueryPageLeaveListVO;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
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
