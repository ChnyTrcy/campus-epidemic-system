package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import static xyz.chnytrcy.campusepidemicsystem.model.constance.FeedbackAcceptanceConstance.RETURN_MESSAGE_LENGTH;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.common.AdminCommon;
import xyz.chnytrcy.campusepidemicsystem.common.TeacherCommon;
import xyz.chnytrcy.campusepidemicsystem.common.UserCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.FeedbackAcceptanceMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AddEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AgreeAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AgreeEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.QueryPageAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.RejectAdminFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.RejectEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.UnableDealEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.FeedbackAcceptance;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.FeedbackAcceptanceEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.TeacherEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.feedbackacceptance.AnalyzeEpidemicFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.AnalyzeAdminFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageAdminFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicMyselfFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.FeedbackAcceptanceService;
import xyz.chnytrcy.core.config.basic.model.BaseId;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
 * @ClassName: FeedbackAcceptanceServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 3:36 PM
 * @Version: 1.0
 */
@Service
public class FeedbackAcceptanceServiceImpl extends ServiceImpl<FeedbackAcceptanceMapper, FeedbackAcceptance>
    implements FeedbackAcceptanceService {

  @Autowired private AdminCommon adminCommon;

  @Autowired private UserCommon userCommon;

  @Autowired private TeacherCommon teacherCommon;

  @Override
  public Result<BasePageVO<QueryPageAdminFeedbackAcceptanceVO>> queryPageAdminFeedbackAcceptance(
      QueryPageAdminFeedbackAcceptanceCommand command) {
    Long code = adminCommon.getAdminId();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<FeedbackAcceptance> list = getBaseMapper().queryPageAdminFeedbackAcceptance(
        command.getFeedbackType(),
        command.getQueryType(),
        code);
    PageInfo pageInfo = new PageInfo(list);
    List<QueryPageAdminFeedbackAcceptanceVO> vos = DozerUtils.convertList(
        list, QueryPageAdminFeedbackAcceptanceVO.class);
    pageInfo.setList(vos);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<Void> agreeAdminFeedbackAcceptance(AgreeAdminFeedbackAcceptanceCommand command) {
    FeedbackAcceptance feedbackAcceptance = this.checkAdminFeedbackAcceptance(command);
    feedbackAcceptance.setResult(FeedbackAcceptanceEnums.RESULT_AGREE.getCode());
    feedbackAcceptance.setIsEnd(FeedbackAcceptanceEnums.IS_END_YES.getCode());
    this.fillAdminReturnMessage(feedbackAcceptance,command.getMessageReturn());
    this.getBaseMapper().updateById(feedbackAcceptance);
    return ResultFactory.successResult();
  }

  @Override
  public Result<Void> rejectAdminFeedbackAcceptance(RejectAdminFeedbackAcceptanceCommand command) {
    FeedbackAcceptance feedbackAcceptance = this.checkAdminFeedbackAcceptance(command);
    feedbackAcceptance.setResult(FeedbackAcceptanceEnums.RESULT_REJECT.getCode());
    feedbackAcceptance.setIsEnd(FeedbackAcceptanceEnums.IS_END_YES.getCode());
    this.fillAdminReturnMessage(feedbackAcceptance,command.getMessageReturn());
    this.getBaseMapper().updateById(feedbackAcceptance);
    return ResultFactory.successResult();
  }

  @Override
  public Result<BasePageVO<QueryPageEpidemicFeedbackAcceptanceVO>> queryPageEpidemicFeedbackAcceptance(
      QueryPageEpidemicFeedbackAcceptanceCommand command) {
    String account = userCommon.getAccount();
    PageHelper.startPage(command.getPageNum(), command.getPageSize());
    List<QueryPageEpidemicFeedbackAcceptanceVO> vos = getBaseMapper().queryPageEpidemicFeedbackAcceptance(
        command.getFeedbackType(), account, command.getIsSponsor());
    vos.forEach(e -> {
      if(e.getFeedbackAcceptanceType().equals(FeedbackAcceptanceEnums.PRODUCER_TYPE_EPIDEMIC_PREVENTION.getCode())){
        e.setProducerCode(null);
        e.setProducerName(null);
      }
    });
    PageInfo pageInfo = new PageInfo(vos);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<AnalyzeAdminFeedbackAcceptanceVO> analyzeAdminFeedbackAcceptance() {
    HashMap<Integer,Integer> hashMap = new HashMap<>();
    Long adminId = adminCommon.getAdminId();
    long undoneCount = getBaseMapper().selectCount(
        new LambdaQueryWrapper<FeedbackAcceptance>()
            .eq(FeedbackAcceptance::getAdminCode, adminId)
            .eq(FeedbackAcceptance::getIsEnd, FeedbackAcceptanceEnums.IS_END_NO.getCode())
            .eq(FeedbackAcceptance::getAdminRelatedTag,FeedbackAcceptanceEnums.ADMIN_RELATED_TAG_YES.getCode()));
    long solvedCount = getBaseMapper().selectCount(new LambdaQueryWrapper<FeedbackAcceptance>()
        .eq(FeedbackAcceptance::getAdminCode, adminId)
        .eq(FeedbackAcceptance::getResult, FeedbackAcceptanceEnums.RESULT_AGREE.getCode())
        .eq(FeedbackAcceptance::getIsEnd, FeedbackAcceptanceEnums.IS_END_YES.getCode())
        .eq(FeedbackAcceptance::getAdminRelatedTag,
            FeedbackAcceptanceEnums.ADMIN_RELATED_TAG_YES.getCode()));
    long allCount = getBaseMapper().selectCount(new LambdaQueryWrapper<FeedbackAcceptance>()
        .eq(FeedbackAcceptance::getAdminCode, adminId)
        .eq(FeedbackAcceptance::getAdminRelatedTag,
            FeedbackAcceptanceEnums.ADMIN_RELATED_TAG_YES.getCode()));
    AnalyzeAdminFeedbackAcceptanceVO key = new AnalyzeAdminFeedbackAcceptanceVO(
        (int) undoneCount,(int) solvedCount,(int) allCount
    );
    return ResultFactory.successResult(key);
  }

  @Override
  public Result<Void> agreeEpidemicFeedbackAcceptance(AgreeEpidemicFeedbackAcceptanceCommand command) {
    FeedbackAcceptance feedbackAcceptance = getOne(command);
    feedbackAcceptance.setResult(FeedbackAcceptanceEnums.RESULT_AGREE.getCode());
    feedbackAcceptance.setIsEnd(FeedbackAcceptanceEnums.IS_END_YES.getCode());
    fillReturnMessage(feedbackAcceptance, command.getReturnMessage());
    getBaseMapper().updateById(feedbackAcceptance);
    return ResultFactory.successResult();
  }

  @Override
  public Result<Void> rejectEpidemicFeedbackAcceptance(RejectEpidemicFeedbackAcceptanceCommand command) {
    FeedbackAcceptance feedbackAcceptance = getOne(command);
    feedbackAcceptance.setResult(FeedbackAcceptanceEnums.RESULT_REJECT.getCode());
    feedbackAcceptance.setIsEnd(FeedbackAcceptanceEnums.IS_END_YES.getCode());
    fillReturnMessage(feedbackAcceptance, command.getReturnMessage());
    getBaseMapper().updateById(feedbackAcceptance);
    return ResultFactory.successResult();
  }

  @Override
  public Result<Void> unableDealEpidemicFeedbackAcceptance(
      UnableDealEpidemicFeedbackAcceptanceCommand command) {
    FeedbackAcceptance feedbackAcceptance = getOne(command);
    feedbackAcceptance.setResult(FeedbackAcceptanceEnums.RESULT_NOT_HANDLER.getCode());
    feedbackAcceptance.setAdminRelatedTag(FeedbackAcceptanceEnums.ADMIN_RELATED_TAG_YES.getCode());
    fillReturnMessage(feedbackAcceptance, command.getReturnMessage());
    getBaseMapper().updateById(feedbackAcceptance);
    return ResultFactory.successResult();
  }

  @Override
  public Result<Void> addEpidemicFeedbackAcceptance(AddEpidemicFeedbackAcceptanceCommand command) {
    Teacher teacher = teacherCommon.getTeacher();
    //该教职工不是防疫人员的情况
    if(teacher.getEpidemicMark().equals(TeacherEnums.EPIDEMIC_MARK_NO.getNumber())){
      throw new BusinessException(BusinessError.TEACHER_IS_NOT_EPIDEMIC_PREVENTION_ERROR);
    }
    FeedbackAcceptance feedbackAcceptance = new FeedbackAcceptance();
    feedbackAcceptance.setProducerCode(teacher.getCode());
    feedbackAcceptance.setProducerName(teacher.getName());
    feedbackAcceptance.setProducerType(FeedbackAcceptanceEnums.PRODUCER_TYPE_EPIDEMIC_PREVENTION.getCode());
    feedbackAcceptance.setAdminCode(adminCommon.getAdminCodeByDept(teacher.getDeptCode()));
    feedbackAcceptance.setType(command.getType());
    feedbackAcceptance.setMessage(command.getMessage());
    feedbackAcceptance.setAdminRelatedTag(FeedbackAcceptanceEnums.ADMIN_RELATED_TAG_YES.getCode());
    getBaseMapper().insert(feedbackAcceptance);
    return ResultFactory.successResult();
  }

  @Override
  public Result<BasePageVO<QueryPageEpidemicMyselfFeedbackAcceptanceVO>> queryPageEpidemicMyselfFeedbackAcceptance(
      BasePageCommand command) {
    Teacher teacher = teacherCommon.getTeacher();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<FeedbackAcceptance> feedbackAcceptances = getBaseMapper().selectList(
        new LambdaQueryWrapper<FeedbackAcceptance>()
            .eq(FeedbackAcceptance::getProducerCode, teacher.getCode()));
    PageInfo pageInfo = new PageInfo(feedbackAcceptances);
    List<QueryPageEpidemicMyselfFeedbackAcceptanceVO> vos = DozerUtils.convertList(
        feedbackAcceptances, QueryPageEpidemicMyselfFeedbackAcceptanceVO.class);
    pageInfo.setList(vos);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<AnalyzeEpidemicFeedbackAcceptanceVO> analyzeEpidemicFeedbackAcceptance() {
    String deptCode = teacherCommon.getEpidemicDeptCode();
    List<FeedbackAcceptance> list = getBaseMapper().analyzeEpidemicFeedbackAcceptance(deptCode);
    int undoneCount = 0,solvedCount = 0;
    int allCount = list.size();
    for (FeedbackAcceptance feedbackAcceptance : list) {
      switch (feedbackAcceptance.getResult()){
        case 0:undoneCount++;break;
        case 1:solvedCount++;break;
        default:break;
      }
    }
    AnalyzeEpidemicFeedbackAcceptanceVO key = new AnalyzeEpidemicFeedbackAcceptanceVO(
        undoneCount,solvedCount,allCount
    );
    return ResultFactory.successResult(key);
  }

  /**
   * 检查管理员处理该反馈受理是否合法
   * @param command
   * @return 反馈受理实体类
   */
  private FeedbackAcceptance checkAdminFeedbackAcceptance(BaseId command){
    FeedbackAcceptance byId = this.getById(command);
    //不存在的情况
    if(Objects.isNull(byId)){
      throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_IS_NOT_EXIST_ERROR);
    }
    if(byId.getAdminRelatedTag().equals(FeedbackAcceptanceEnums.ADMIN_RELATED_TAG_NO.getCode())){
      throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_ILLEGAL_DETAIL_ERROR);
    }
    switch (byId.getResult()){
      case 1: case 2:
        //已经受理完毕的情况
        throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_END_ERROR);
      default:break;
    }
    //处理不属于自己管辖的反馈受理类
    if(!adminCommon.getAdminId().equals(byId.getAdminCode())){
      throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_NOT_DETAIL_THEMSELF_ERROR);
    }
    return byId;
  }


  /**
   * 检查防疫人员处理该条反馈受理是否合理
   */
  private FeedbackAcceptance getOne(BaseId string){
    FeedbackAcceptance feedbackAcceptance = getBaseMapper().selectOne(
        new LambdaQueryWrapper<FeedbackAcceptance>()
            .eq(FeedbackAcceptance::getId, string.getId()));
    if(Objects.isNull(feedbackAcceptance)){
      throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_IS_NOT_EXIST_ERROR);
    }
    if(!feedbackAcceptance.getResult().equals(FeedbackAcceptanceEnums.RESULT_UNREAD.getCode())){
      throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_UNABLE_PROCESS_TRANSACTION_HAS_ALREADY_PROCESSED_ERROR);
    }
    if(feedbackAcceptance.getIsEnd().equals(FeedbackAcceptanceEnums.IS_END_YES.getCode())){
      throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_END_ERROR);
    }
    return feedbackAcceptance;
  }

  /**
   * 填充回复信息
   */
  private void fillReturnMessage(FeedbackAcceptance feedbackAcceptance,String message){
    if(StringUtils.isNotBlank(message)){
      if(message.length() >= RETURN_MESSAGE_LENGTH){
        throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_RETURN_MESSAGE_LENGTH_LONG_ERROR);
      }
      feedbackAcceptance.setMessageReturn("  [防疫人员]:" + message);
    }
  }

  private void fillAdminReturnMessage(FeedbackAcceptance feedbackAcceptance,String message){
    if(StringUtils.isNotBlank(message)){
      if(message.length() >= RETURN_MESSAGE_LENGTH){
        throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_RETURN_MESSAGE_LENGTH_LONG_ERROR);
      }
      String messageReturn = feedbackAcceptance.getMessageReturn();
      feedbackAcceptance.setMessageReturn(messageReturn + "  [管理员]:" + message);
    }
  }

}
