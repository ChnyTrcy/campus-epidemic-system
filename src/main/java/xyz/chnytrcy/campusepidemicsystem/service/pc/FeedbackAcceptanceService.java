package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AddEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AgreeAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AgreeEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.QueryPageAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.RejectAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.RejectEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.UnableDealEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.FeedbackAcceptance;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.feedbackacceptance.AnalyzeEpidemicFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.AnalyzeAdminFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageAdminFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicMyselfFeedbackAcceptanceVO;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc
 * @InterfaceName: FeedbackAcceptanceService
 * @Author: ChnyTrcy
 * @Description: 反馈受理服务接口
 * @Date: 2022/8/24 3:36 PM
 * @Version: 1.0
 */
public interface FeedbackAcceptanceService extends IService<FeedbackAcceptance> {

  /**
   * 分页查询反馈受理
   */
  Result<BasePageVO<QueryPageAdminFeedbackAcceptanceVO>> queryPageAdminFeedbackAcceptance(
      QueryPageAdminFeedbackAcceptanceCommand command);

  /**
   * 管理员同意反馈
   */
  Result<Void> agreeAdminFeedbackAcceptance(AgreeAdminFeedbackAcceptanceCommand command);

  /**
   * 管理员拒绝反馈
   */
  Result<Void> rejectAdminFeedbackAcceptance(RejectAdminFeedbackAcceptanceCommand command);

  /**
   * 防疫人员查看与自己相关的反馈受理
   */
  Result<BasePageVO<QueryPageEpidemicFeedbackAcceptanceVO>> queryPageEpidemicFeedbackAcceptance
  (QueryPageEpidemicFeedbackAcceptanceCommand command);

  /**
   * 统计分析管理员对应的反馈受理
   */
  Result<AnalyzeAdminFeedbackAcceptanceVO> analyzeAdminFeedbackAcceptance();

  /**
   * 防疫人员同意该条受理
   */
  Result<Void> agreeEpidemicFeedbackAcceptance(AgreeEpidemicFeedbackAcceptanceCommand command);

  /**
   * 防疫人员拒绝该条受理
   */
  Result<Void> rejectEpidemicFeedbackAcceptance(RejectEpidemicFeedbackAcceptanceCommand command);

  /**
   * 防疫人员无法处理该条受理
   */
  Result<Void> unableDealEpidemicFeedbackAcceptance(UnableDealEpidemicFeedbackAcceptanceCommand command);

  /**
   * 防疫人员发起反馈受理
   */
  Result<Void> addEpidemicFeedbackAcceptance(AddEpidemicFeedbackAcceptanceCommand command);

  /**
   * 防疫人员查看自己提出的反馈受理
   */
  Result<BasePageVO<QueryPageEpidemicMyselfFeedbackAcceptanceVO>> queryPageEpidemicMyselfFeedbackAcceptance(
      BasePageCommand command);

  /**
   * 防疫人员分析与自己相关的反馈受理
   */
  Result<AnalyzeEpidemicFeedbackAcceptanceVO> analyzeEpidemicFeedbackAcceptance();
}
