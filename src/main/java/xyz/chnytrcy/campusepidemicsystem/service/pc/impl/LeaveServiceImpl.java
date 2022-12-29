package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.chnytrcy.campusepidemicsystem.common.AdminCommon;
import xyz.chnytrcy.campusepidemicsystem.converter.LeaveConverter;
import xyz.chnytrcy.campusepidemicsystem.mapper.LeaveDetailMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.LeaveMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.AgreeLeaveCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.QueryPageLeaveListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.leave.RejectLeaveCommand;
import xyz.chnytrcy.campusepidemicsystem.model.dto.QueryPageLeaveDTO;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Leave;
import xyz.chnytrcy.campusepidemicsystem.model.entity.LeaveDetail;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.LeaveDetailEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.LeaveEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.leave.AnalyzeAdminLeaveVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.leave.QueryPageLeaveListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.LeaveService;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
 * @ClassName: LeaveServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 4:46 PM
 * @Version: 1.0
 */
@Service
public class LeaveServiceImpl extends ServiceImpl<LeaveMapper, Leave>
    implements LeaveService {

  @Autowired private AdminCommon adminCommon;

  @Autowired private LeaveConverter leaveConverter;

  @Autowired private LeaveDetailMapper leaveDetailMapper;

  @Override
  public Result<BasePageVO<QueryPageLeaveListVO>> queryPageLeaveList(QueryPageLeaveListCommand command) {
    //开始时间晚于结束时间
    if(null != command.getStartTime() &&
        null != command.getEndTime() &&
        command.getStartTime().isAfter(command.getEndTime())){
      throw new BusinessException(BusinessError.LEAVE_TIME_START_AFTER_END_ERROR);
    }
    Long adminId = adminCommon.getAdminId();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<QueryPageLeaveDTO> list = getBaseMapper().queryPageLeaveList(command,adminId);
    PageInfo pageInfo = new PageInfo(list);
    List<QueryPageLeaveListVO> vos = leaveConverter.converter(list);
    pageInfo.setList(vos);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> agreeLeave(AgreeLeaveCommand command) {
    Leave leave = checkLeave(command.getId());
    leave.setApprovalResult(LeaveEnums.APPROVAL_RESULT_AGREE.getCode());
    leave.setIsEnd(LeaveEnums.IS_END_YES.getCode());
    this.getBaseMapper().updateById(leave);
    LeaveDetail leaveDetail = new LeaveDetail(
        command.getId(),
        LeaveDetailEnums.TYPE_END.getCode(),
        command.getMessage()
    );
    leaveDetailMapper.insert(leaveDetail);
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> rejectLeave(RejectLeaveCommand command) {
    Leave leave = checkLeave(command.getId());
    leave.setApprovalResult(LeaveEnums.APPROVAL_RESULT_REJECT.getCode());
    leave.setIsEnd(LeaveEnums.IS_END_YES.getCode());
    this.getBaseMapper().updateById(leave);
    LeaveDetail leaveDetail = new LeaveDetail(
        command.getId(),
        LeaveDetailEnums.TYPE_END.getCode(),
        command.getMessage()
    );
    leaveDetailMapper.insert(leaveDetail);
    return ResultFactory.successResult();
  }

  @Override
  public Result<AnalyzeAdminLeaveVO> analyzeAdminLeave() {
    Long adminId = adminCommon.getAdminId();
    long undoneCount = getBaseMapper().selectCount(new LambdaQueryWrapper<Leave>()
        .eq(Leave::getAdminId, adminId)
        .eq(Leave::getIsEnd,LeaveEnums.IS_END_NO.getCode()));
    long solvedCount = getBaseMapper().selectCount(new LambdaQueryWrapper<Leave>()
        .eq(Leave::getAdminId, adminId)
        .eq(Leave::getApprovalResult, LeaveEnums.APPROVAL_RESULT_AGREE.getCode()));
    long allCount = getBaseMapper().selectCount(new LambdaQueryWrapper<Leave>()
        .eq(Leave::getAdminId, adminId));
    AnalyzeAdminLeaveVO key = new AnalyzeAdminLeaveVO(
        (int) undoneCount,(int) solvedCount,(int) allCount
    );
    return ResultFactory.successResult(key);
  }

  /**
   * 检查请假单是否合法
   */
  protected Leave checkLeave(Long id){
    Leave byId = this.getById(id);
    if(null == byId){
      throw new BusinessException(BusinessError.LEAVE_IS_NOT_EXIST_ERROR);
    }
    if(Arrays.asList(LeaveEnums.APPROVAL_RESULT_AGREE.getCode(),LeaveEnums.APPROVAL_RESULT_REJECT.getCode())
        .contains(byId.getApprovalResult())){
      throw new BusinessException(BusinessError.LEAVE_END_ERROR);
    }
    if(!byId.getAdminId().equals(adminCommon.getAdminId())){
      throw new BusinessException(BusinessError.LEAVE_NOT_DETAIL_THEMSELF_ERROR);
    }
    return byId;
  }
}
