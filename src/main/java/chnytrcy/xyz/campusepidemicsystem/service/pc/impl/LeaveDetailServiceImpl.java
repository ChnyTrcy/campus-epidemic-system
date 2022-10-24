package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.common.AdminCommon;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave.DealReturnLeaveCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import chnytrcy.xyz.campusepidemicsystem.model.entity.LeaveDetail;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveDetailEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.LeaveDetailService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: LeaveDetailServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/7 9:23 PM
 * @Version: 1.0
 */
@Service
public class LeaveDetailServiceImpl extends ServiceImpl<LeaveDetailMapper, LeaveDetail>
    implements LeaveDetailService {

  @Autowired private AdminCommon adminCommon;

  @Autowired private LeaveMapper leaveMapper;

  @Value("${leave.restricted.speech}")
  private boolean speak;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> dealReturnLeave(DealReturnLeaveCommand command) {
    if(speak){
      LeaveDetail old = getBaseMapper().selectOne(
        new LambdaQueryWrapper<LeaveDetail>()
            .eq(LeaveDetail::getRelId, command.getId())
            .orderByDesc(LeaveDetail::getCreateTime)
            .last("limit 1"));
      if(!Arrays.asList(LeaveDetailEnums.TYPE_STUDENT.getCode(),LeaveDetailEnums.TYPE_STUDENT_AGAIN.getCode())
          .contains(old.getType())){
        throw new BusinessException(BusinessError.LEAVE_DETAIL_NOT_FOUND_STUDENT_ERROR);
      }
    }
    Long adminId = adminCommon.getAdminId();
    Long id = command.getId();
    Leave leave = leaveMapper.selectOne(
        new LambdaQueryWrapper<Leave>()
            .eq(Leave::getId, id));
    if(leave.getIsEnd().equals(LeaveEnums.IS_END_YES.getCode())){
      throw new BusinessException(BusinessError.LEAVE_RETURN_MESSAGE_IS_END_ERROR);
    }
    if(!leave.getAdminId().equals(adminId)){
      throw new BusinessException(BusinessError.LEAVE_NOT_DETAIL_THEMSELF_ERROR);
    }
    Integer code = LeaveEnums.APPROVAL_RESULT_DEAL.getCode();
    leave.setApprovalResult(code);
    leaveMapper.updateById(leave);
    LeaveDetail leaveDetail = new LeaveDetail(
        command.getId(),
        LeaveDetailEnums.TYPE_ADMIN_REPEAT.getCode(),
        command.getMessage()
    );
    getBaseMapper().insert(leaveDetail);
    return ResultFactory.successResult();
  }
}
