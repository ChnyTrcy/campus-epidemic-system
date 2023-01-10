package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.chnytrcy.campusepidemicsystem.common.AdminCommon;
import xyz.chnytrcy.campusepidemicsystem.common.StudentCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.FeedbackAcceptanceMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance.AddCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance.AddFeedbackAcceptanceByIsolationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance.AgreeCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance.QueryPageEpidemicAppFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance.QueryPageFeedbackAcceptanceByIsolationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance.RejectCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.feedbackacceptance.UnableDealCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.feedbackacceptance.AddEpidemicFeedbackAcceptanceCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.FeedbackAcceptance;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.FeedbackAcceptanceEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.feedbackacceptance.AnalyzeEpidemicFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.feedbackacceptance.QueryPageFeedbackAcceptanceByIsolationVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.feedbackacceptance.QueryPageEpidemicFeedbackAcceptanceVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.FeedbackAcceptanceAppService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.impl.FeedbackAcceptanceServiceImpl;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
 * @ClassName: FeedbackAcceptanceAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/10 4:11 PM
 * @Version: 1.0
 */
@Service
public class FeedbackAcceptanceAppServiceImpl extends ServiceImpl<FeedbackAcceptanceMapper, FeedbackAcceptance>
    implements FeedbackAcceptanceAppService {

  @Autowired private StudentMapper studentMapper;

  @Autowired private StudentCommon studentCommon;

  @Autowired private AdminCommon adminCommon;

  @Autowired private FeedbackAcceptanceServiceImpl feedbackAcceptanceService;

  @Override
  public Result<Void> agree(AgreeCommand command) {
    return feedbackAcceptanceService.agreeEpidemicFeedbackAcceptance(command);
  }

  @Override
  public Result<Void> reject(RejectCommand command) {
    return feedbackAcceptanceService.rejectEpidemicFeedbackAcceptance(command);
  }

  @Override
  public Result<Void> unableDeal(UnableDealCommand command) {
    return feedbackAcceptanceService.unableDealEpidemicFeedbackAcceptance(command);
  }

  @Override
  public Result<Void> addEpidemicAppFeedbackAcceptance(AddCommand command) {
    AddEpidemicFeedbackAcceptanceCommand convert = DozerUtils.convert(command,
        AddEpidemicFeedbackAcceptanceCommand.class);
    return feedbackAcceptanceService.addEpidemicFeedbackAcceptance(convert);
  }

  @Override
  public Result<BasePageVO<QueryPageEpidemicFeedbackAcceptanceVO>>
  queryPageEpidemicAppFeedbackAcceptance(
      QueryPageEpidemicAppFeedbackAcceptanceCommand command) {
    return feedbackAcceptanceService.queryPageEpidemicFeedbackAcceptance(command);
  }

  @Override
  public Result<AnalyzeEpidemicFeedbackAcceptanceVO> analyzeEpidemicFeedbackAcceptance() {
    return feedbackAcceptanceService.analyzeEpidemicFeedbackAcceptance();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> addFeedbackAcceptanceByIsolation(
      AddFeedbackAcceptanceByIsolationCommand command) {
    String studentCode = studentCommon.getStudentCode();
    check(studentCode);
    Student student = studentMapper.selectOne(
        new LambdaQueryWrapper<Student>()
            .eq(Student::getCode, studentCode));
    Long adminCodeByDept = adminCommon.getAdminCodeByDept(student.getDeptCode());
    FeedbackAcceptance feedbackAcceptance = new FeedbackAcceptance(
      studentCode,student.getName(),FeedbackAcceptanceEnums.PRODUCER_TYPE_ISOLATION.getCode(),
        adminCodeByDept,command.getType(),command.getMessage(),null,
        FeedbackAcceptanceEnums.RESULT_UNREAD.getCode(), FeedbackAcceptanceEnums.IS_END_NO.getCode(),
        FeedbackAcceptanceEnums.ADMIN_RELATED_TAG_NO.getCode()
    );
    getBaseMapper().insert(feedbackAcceptance);
    return ResultFactory.successResult();
  }

  @Override
  public Result<BasePageVO<QueryPageFeedbackAcceptanceByIsolationVO>> queryPageFeedbackAcceptanceByIsolation(
      QueryPageFeedbackAcceptanceByIsolationCommand command) {
    String studentCode = studentCommon.getStudentCode();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<FeedbackAcceptance> feedbackAcceptances = getBaseMapper().selectList(
        new LambdaQueryWrapper<FeedbackAcceptance>()
            .eq(FeedbackAcceptance::getProducerCode, studentCode)
            .eq(FeedbackAcceptance::getProducerType,
                FeedbackAcceptanceEnums.PRODUCER_TYPE_ISOLATION.getCode()));
    PageInfo pageInfo = new PageInfo(feedbackAcceptances);
    List<QueryPageFeedbackAcceptanceByIsolationVO> key = DozerUtils.convertList(
        feedbackAcceptances, QueryPageFeedbackAcceptanceByIsolationVO.class);
    pageInfo.setList(key);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  private void check(String code){
    List<FeedbackAcceptance> feedbackAcceptances = getBaseMapper().selectList(
        new LambdaQueryWrapper<FeedbackAcceptance>()
            .eq(FeedbackAcceptance::getProducerCode, code)
            .eq(FeedbackAcceptance::getIsEnd,FeedbackAcceptanceEnums.IS_END_NO));
    if(CollUtil.isNotEmpty(feedbackAcceptances)){
      throw new BusinessException(BusinessError.FEEDBACK_ACCEPTANCE_UNTREATED_ERROR);
    }
  }

}
