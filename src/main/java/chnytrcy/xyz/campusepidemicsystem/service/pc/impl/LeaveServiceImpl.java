package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.common.AdminCommon;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.converter.LeaveConverter;
import chnytrcy.xyz.campusepidemicsystem.mapper.AdminMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.AgreeLeaveCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.QueryPageLeaveListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.RejectLeaveCommand;
import chnytrcy.xyz.campusepidemicsystem.model.dto.QueryPageLeaveDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import chnytrcy.xyz.campusepidemicsystem.model.entity.LeaveDetail;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveDetailEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.StudentEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave.AnalyzeAdminLeaveVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave.QueryPageLeaveListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.LeaveService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StreetService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
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
