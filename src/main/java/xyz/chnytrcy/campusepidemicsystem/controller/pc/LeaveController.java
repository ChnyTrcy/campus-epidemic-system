package xyz.chnytrcy.campusepidemicsystem.controller.pc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.AgreeLeaveCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.DealReturnLeaveCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.QueryPageLeaveListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.RejectLeaveCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.leave.AnalyzeAdminLeaveVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.leave.QueryPageLeaveListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.LeaveDetailService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.LeaveService;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.pc
 * @ClassName: LeaveController
 * @Author: ChnyTrcy
 * @Description: 请假Controller
 * @Date: 2022/8/30 3:30 PM
 * @Version: 1.0
 */
@RequestMapping("${mvc.url.perfix.pc}/leave")
@RestController
@Api(value = "请假Controller",tags = "PC - 请假接口")
public class LeaveController {

  @Autowired private LeaveService leaveService;

  @Autowired private LeaveDetailService leaveDetailService;

  @GetMapping("/queryPageLeaveList")
  @RequiresPermissions("admin:leave:select")
  @ApiOperation("查看本院系的请假列表")
  public Result<BasePageVO<QueryPageLeaveListVO>> queryPageLeaveList(@Valid QueryPageLeaveListCommand command){
    return leaveService.queryPageLeaveList(command);
  }

  @GetMapping("/agreeLeave")
  @RequiresPermissions("admin:leave:update")
  @ApiOperation("同意请假")
  public Result<Void> agreeLeave(@Valid AgreeLeaveCommand command){
    return leaveService.agreeLeave(command);
  }

  @GetMapping("/rejectLeave")
  @RequiresPermissions("admin:leave:update")
  @ApiOperation("拒绝请假")
  public Result<Void> rejectLeave(@Valid RejectLeaveCommand command){
    return leaveService.rejectLeave(command);
  }

  @GetMapping("/analyzeAdminLeave")
  @RequiresPermissions("admin:leave:select")
  @ApiOperation("统计分析请假数")
  public Result<AnalyzeAdminLeaveVO> analyzeAdminLeave(){
    return leaveService.analyzeAdminLeave();
  }

  @PostMapping("/dealReturnLeave")
  @RequiresPermissions("admin:leave:update")
  @ApiOperation("管理员回复请假申请")
  public Result<Void> dealReturnLeave(@RequestBody @Valid DealReturnLeaveCommand command){
    return leaveDetailService.dealReturnLeave(command);
  }
}
