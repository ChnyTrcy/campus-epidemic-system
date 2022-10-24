package chnytrcy.xyz.campusepidemicsystem.service.app;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.AddCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.AddFeedbackAcceptanceByIsolationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.AgreeCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.QueryPageEpidemicAppFeedbackAcceptanceCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.QueryPageFeedbackAcceptanceByIsolationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.RejectCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance.UnableDealCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.FeedbackAcceptance;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.feedbackacceptance.AnalyzeEpidemicFeedbackAcceptanceVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.feedbackacceptance.QueryPageFeedbackAcceptanceByIsolationVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app
 * @InterfaceName: FeedbackAcceptanceAppService
 * @Author: ChnyTrcy
 * @Description: 移动端反馈受理服务接口
 * @Date: 2022/9/10 4:11 PM
 * @Version: 1.0
 */
public interface FeedbackAcceptanceAppService extends IService<FeedbackAcceptance> {

  /**
   * 同意反馈受理
   */
  Result<Void> agree(AgreeCommand command);

  /**
   * 拒绝反馈受理
   */
  Result<Void> reject(RejectCommand command);

  /**
   * 无法处理反馈受理
   */
  Result<Void> unableDeal(UnableDealCommand command);

  /**
   * 添加防疫人员的反馈
   */
  Result<Void> addEpidemicAppFeedbackAcceptance(AddCommand command);

  /**
   * 防疫人员查看与自己相关的反馈受理
   */
  Result<BasePageVO<QueryPageEpidemicFeedbackAcceptanceVO>> queryPageEpidemicAppFeedbackAcceptance
  (QueryPageEpidemicAppFeedbackAcceptanceCommand command);

  /**
   * 防疫人员分析与自己相关的反馈受理
   */
  Result<AnalyzeEpidemicFeedbackAcceptanceVO> analyzeEpidemicFeedbackAcceptance();

  /**
   * 隔离人员提出反馈受理
   */
  Result<Void> addFeedbackAcceptanceByIsolation(AddFeedbackAcceptanceByIsolationCommand command);

  /**
   * 隔离人员分页查看反馈受理（精简）
   */
  Result<BasePageVO<QueryPageFeedbackAcceptanceByIsolationVO>> queryPageFeedbackAcceptanceByIsolation(
      QueryPageFeedbackAcceptanceByIsolationCommand command);
}
