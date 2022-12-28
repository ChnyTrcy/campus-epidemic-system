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
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AddEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AgreeAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AgreeEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.QueryPageAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.RejectAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.RejectEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.UnableDealEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.feedbackacceptance.AnalyzeEpidemicFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.AnalyzeAdminFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageAdminFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicMyselfFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.FeedbackAcceptanceService;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.pc
 * @ClassName: FeedbackAcceptanceController
 * @Author: ChnyTrcy
 * @Description: 反馈受理Controller
 * @Date: 2022/8/30 11:12 AM
 * @Version: 1.0
 */
@RequestMapping("/${mvc.url.perfix.pc}/feedbackAcceptance")
@RestController
@Api(value = "反馈受理Controller",tags = "PC - 反馈受理接口")
public class FeedbackAcceptanceController {

  @Autowired private FeedbackAcceptanceService feedbackAcceptanceService;

  @GetMapping("/queryPageAdminFeedbackAcceptance")
  @RequiresPermissions("admin:feedbackAcceptance:select")
  @ApiOperation("分页查询反馈受理")
  public Result<BasePageVO<QueryPageAdminFeedbackAcceptanceVO>> queryPageAdminFeedbackAcceptance(
      @Valid QueryPageAdminFeedbackAcceptanceCommand command){
    return feedbackAcceptanceService.queryPageAdminFeedbackAcceptance(command);
  }

  @GetMapping("/agreeAdminFeedbackAcceptance")
  @RequiresPermissions("admin:feedbackAcceptance:update")
  @ApiOperation("管理员同意反馈")
  public Result<Void> agreeAdminFeedbackAcceptance(@Valid AgreeAdminFeedbackAcceptanceCommand command){
    return feedbackAcceptanceService.agreeAdminFeedbackAcceptance(command);
  }

  @GetMapping("/rejectAdminFeedbackAcceptance")
  @RequiresPermissions("admin:feedbackAcceptance:update")
  @ApiOperation("管理员拒绝反馈")
  public Result<Void> rejectAdminFeedbackAcceptance(@Valid RejectAdminFeedbackAcceptanceCommand command){
    return feedbackAcceptanceService.rejectAdminFeedbackAcceptance(command);
  }

  @GetMapping("/queryPageEpidemicFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:select")
  @ApiOperation("防疫人员查看与自己相关的反馈受理")
  public Result<BasePageVO<QueryPageEpidemicFeedbackAcceptanceVO>> queryPageEpidemicFeedbackAcceptance(@Valid
      QueryPageEpidemicFeedbackAcceptanceCommand command){
    return feedbackAcceptanceService.queryPageEpidemicFeedbackAcceptance(command);
  }

  @GetMapping("/analyzeAdminFeedbackAcceptance")
  @RequiresPermissions("admin:feedbackAcceptance:select")
  @ApiOperation("统计分析管理员对应的反馈受理")
  public Result<AnalyzeAdminFeedbackAcceptanceVO> analyzeAdminFeedbackAcceptance(){
    return feedbackAcceptanceService.analyzeAdminFeedbackAcceptance();
  }

  @GetMapping("/agreeEpidemicFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:update")
  @ApiOperation("防疫人员同意该条受理")
  public Result<Void> agreeEpidemicFeedbackAcceptance(@Valid AgreeEpidemicFeedbackAcceptanceCommand command){
    return feedbackAcceptanceService.agreeEpidemicFeedbackAcceptance(command);
  }

  @GetMapping("/rejectEpidemicFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:update")
  @ApiOperation("防疫人员拒绝该条受理")
  public Result<Void> rejectEpidemicFeedbackAcceptance(@Valid RejectEpidemicFeedbackAcceptanceCommand command){
    return feedbackAcceptanceService.rejectEpidemicFeedbackAcceptance(command);
  }

  @GetMapping("/unableDealEpidemicFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:update")
  @ApiOperation("防疫人员无法处理该条受理")
  public Result<Void> unableDealEpidemicFeedbackAcceptance(@Valid UnableDealEpidemicFeedbackAcceptanceCommand command){
    return feedbackAcceptanceService.unableDealEpidemicFeedbackAcceptance(command);
  }

  @PostMapping("/addEpidemicFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:insert")
  @ApiOperation("防疫人员发起反馈受理")
  public Result<Void> addEpidemicFeedbackAcceptance(@Valid @RequestBody AddEpidemicFeedbackAcceptanceCommand command){
    return feedbackAcceptanceService.addEpidemicFeedbackAcceptance(command);
  }

  @GetMapping("/queryPageEpidemicMyselfFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:select")
  @ApiOperation("防疫人员查看自己提出的反馈受理")
  public Result<BasePageVO<QueryPageEpidemicMyselfFeedbackAcceptanceVO>> queryPageEpidemicMyselfFeedbackAcceptance(
      @Valid BasePageCommand command){
    return feedbackAcceptanceService.queryPageEpidemicMyselfFeedbackAcceptance(command);
  }

  @GetMapping("/analyzeEpidemicFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:select")
  @ApiOperation("防疫人员分析需要自己处理的反馈受理")
  public Result<AnalyzeEpidemicFeedbackAcceptanceVO> analyzeEpidemicFeedbackAcceptance(){
    return feedbackAcceptanceService.analyzeEpidemicFeedbackAcceptance();
  }

}
