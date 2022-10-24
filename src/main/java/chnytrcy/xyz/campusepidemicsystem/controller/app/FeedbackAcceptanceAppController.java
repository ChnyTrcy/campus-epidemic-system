package chnytrcy.xyz.campusepidemicsystem.controller.app;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.AddCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.AddFeedbackAcceptanceByIsolationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.AgreeCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.QueryPageEpidemicAppFeedbackAcceptanceCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.QueryPageFeedbackAcceptanceByIsolationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.RejectCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.UnableDealCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.feedbackacceptance.AnalyzeEpidemicFeedbackAcceptanceVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.feedbackacceptance.QueryPageFeedbackAcceptanceByIsolationVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.FeedbackAcceptanceAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
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

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.app
 * @ClassName: FeedbackAcceptanceAppController
 * @Author: ChnyTrcy
 * @Description: 反馈受理Controller
 * @Date: 2022/9/10 4:07 PM
 * @Version: 1.0
 */
@Api(value = "反馈受理Controller",tags = "App - 反馈受理接口")
@RestController
@RequestMapping("${mvc.url.perfix.app}/feedbackAcceptance")
public class FeedbackAcceptanceAppController {

  @Autowired private FeedbackAcceptanceAppService feedbackAcceptanceAppService;

  @GetMapping("/agree")
  @RequiresPermissions("epidemic:feedbackAcceptance:update")
  @ApiOperation("同意反馈受理")
  public Result<Void> agree(@Valid AgreeCommand command){
    return feedbackAcceptanceAppService.agree(command);
  }

  @GetMapping("/reject")
  @RequiresPermissions("epidemic:feedbackAcceptance:update")
  @ApiOperation("拒绝反馈受理")
  public Result<Void> reject(@Valid RejectCommand command){
    return feedbackAcceptanceAppService.reject(command);
  }

  @GetMapping("/unableDeal")
  @RequiresPermissions("epidemic:feedbackAcceptance:update")
  @ApiOperation("无法处理反馈受理")
  public Result<Void> unableDeal(@Valid UnableDealCommand command){
    return feedbackAcceptanceAppService.unableDeal(command);
  }

  @PostMapping("/addEpidemicAppFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:insert")
  @ApiOperation("添加防疫人员的反馈")
  public Result<Void> addEpidemicAppFeedbackAcceptance(@Valid @RequestBody AddCommand command){
    return feedbackAcceptanceAppService.addEpidemicAppFeedbackAcceptance(command);
  }

  @GetMapping("/queryPageEpidemicAppFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:select")
  @ApiOperation("防疫人员查看与自己相关的反馈受理")
  public Result<BasePageVO<QueryPageEpidemicFeedbackAcceptanceVO>> queryPageEpidemicAppFeedbackAcceptance(
      @Valid QueryPageEpidemicAppFeedbackAcceptanceCommand command){
    return feedbackAcceptanceAppService.queryPageEpidemicAppFeedbackAcceptance(command);
  }

  @GetMapping("/analyzeEpidemicFeedbackAcceptance")
  @RequiresPermissions("epidemic:feedbackAcceptance:select")
  @ApiOperation("统计分析防疫人员需要自己处理的反馈受理数量分布")
  public Result<AnalyzeEpidemicFeedbackAcceptanceVO> analyzeEpidemicFeedbackAcceptance(){
    return feedbackAcceptanceAppService.analyzeEpidemicFeedbackAcceptance();
  }

  @PostMapping("/addFeedbackAcceptanceByIsolation")
  @RequiresPermissions("isolation:feedbackAcceptance:insert")
  @ApiOperation("隔离人员提出反馈受理")
  public Result<Void> addFeedbackAcceptanceByIsolation(
      @RequestBody @Valid AddFeedbackAcceptanceByIsolationCommand command){
    return feedbackAcceptanceAppService.addFeedbackAcceptanceByIsolation(command);
  }

  @GetMapping("/queryPageFeedbackAcceptanceByIsolation")
  @RequiresPermissions("isolation:feedbackAcceptance:select")
  @ApiOperation("隔离人员分页查看反馈受理（精简）")
  public Result<BasePageVO<QueryPageFeedbackAcceptanceByIsolationVO>> queryPageFeedbackAcceptanceByIsolation(
      QueryPageFeedbackAcceptanceByIsolationCommand command){
    return feedbackAcceptanceAppService.queryPageFeedbackAcceptanceByIsolation(command);
  }

}
