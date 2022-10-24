package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import chnytrcy.xyz.campusepidemicsystem.common.StudentCommon;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.ReturnLeaveToAdminMessageCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import chnytrcy.xyz.campusepidemicsystem.model.entity.LeaveDetail;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveDetailEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveEnums;
import chnytrcy.xyz.campusepidemicsystem.service.app.LeaveDetailAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
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
