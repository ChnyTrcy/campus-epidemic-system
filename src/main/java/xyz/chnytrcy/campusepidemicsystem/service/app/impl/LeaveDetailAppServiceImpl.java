package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.common.StudentCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.LeaveDetailMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.LeaveMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.leave.ReturnLeaveToAdminMessageCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Leave;
import xyz.chnytrcy.campusepidemicsystem.model.entity.LeaveDetail;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.LeaveDetailEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.LeaveEnums;
import xyz.chnytrcy.campusepidemicsystem.service.app.LeaveDetailAppService;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
 * @ClassName: LeaveDetailAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/8 12:22 AM
 * @Version: 1.0
 */
@Service
public class LeaveDetailAppServiceImpl extends ServiceImpl<LeaveDetailMapper, LeaveDetail>
    implements LeaveDetailAppService {

  @Value("${leave.restricted.speech}")
  private boolean speak;

  @Autowired private StudentCommon studentCommon;

  @Autowired private LeaveMapper leaveMapper;

  @Override
  public Result<Void> returnLeaveToAdminMessage(ReturnLeaveToAdminMessageCommand command) {
    if(speak){
      LeaveDetail old = getBaseMapper().selectOne(
          new LambdaQueryWrapper<LeaveDetail>()
              .eq(LeaveDetail::getRelId, command.getId())
              .orderByDesc(LeaveDetail::getCreateTime)
              .last("limit 1"));
      if(!LeaveDetailEnums.TYPE_ADMIN_REPEAT.getCode().equals(old.getType())){
        throw new BusinessException(BusinessError.LEAVE_DETAIL_NOT_FOUNT_ADMIN_ERROR);
      }
    }
    String studentCode = studentCommon.getStudentCode();
    Leave leave = leaveMapper.selectOne(
        new LambdaQueryWrapper<Leave>()
            .eq(Leave::getId, command.getId()));
    if(leave.getIsEnd().equals(LeaveEnums.IS_END_YES.getCode())){
      throw new BusinessException(BusinessError.LEAVE_RETURN_MESSAGE_IS_END_ERROR);
    }
    if(!leave.getCode().equals(studentCode)){
      throw new BusinessException(BusinessError.STUDENT_DEAL_NOT_MYSELF_LEAVE_ERROR);
    }
    LeaveDetail leaveDetail = new LeaveDetail(
        command.getId(),
        LeaveDetailEnums.TYPE_STUDENT_AGAIN.getCode(),
        command.getMessage()
    );
    getBaseMapper().insert(leaveDetail);
    return ResultFactory.successResult();
  }
}
