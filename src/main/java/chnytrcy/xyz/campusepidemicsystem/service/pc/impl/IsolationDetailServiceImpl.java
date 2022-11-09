package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationPersonMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail.AddIsolationDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail.QueryIsolationDetailByIdCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.isolationdetail.ReviseIsolationDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.constance.IsolationDetailConstance;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationDetail;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.isolationdetail.QueryIsolationDetailByIdVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.IsolationDetailService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: IsolationDetailServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 3:56 PM
 * @Version: 1.0
 */
@Service
public class IsolationDetailServiceImpl extends ServiceImpl<IsolationDetailMapper, IsolationDetail>
    implements IsolationDetailService {

  @Autowired private IsolationPersonMapper isolationPersonMapper;

  @Override
  public Result<List<QueryIsolationDetailByIdVO>> queryIsolationDetailById(
      QueryIsolationDetailByIdCommand command) {
    List<IsolationDetail> isolationDetails = getBaseMapper().selectList(
        new LambdaQueryWrapper<IsolationDetail>()
            .eq(IsolationDetail::getRelId, command.getId()));
    List<QueryIsolationDetailByIdVO> vos = DozerUtils.convertList(
        isolationDetails, QueryIsolationDetailByIdVO.class);
    return ResultFactory.successResult(vos);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> addIsolationDetail(AddIsolationDetailCommand command) {
    IsolationPerson isolationPerson = isolationPersonMapper.selectById(command.getId());
    IsolationDetail isolationDetail = new IsolationDetail(
        isolationPerson.getCode(),
        isolationPerson.getName(),
        command.getTemperature(),
        command.getId()
    );
    getBaseMapper().insert(isolationDetail);
    this.checkIsDanger(command.getId());
    return ResultFactory.successResult();
  }

  @Override
  public Result<Void> reviseIsolationDetail(ReviseIsolationDetailCommand command) {
    IsolationDetail isolationDetail = new IsolationDetail();
    isolationDetail.setId(command.getId());
    isolationDetail.setTemperature(command.getTemperature());
    getBaseMapper().updateById(isolationDetail);
    this.checkIsDanger(command.getId());
    return ResultFactory.successResult();
  }

  /**
   * 检查该隔离人员体温是否超过3天异常
   * @param relId 隔离人员Id
   */
  private void checkIsDanger(Long relId){
    int i = 0;
    List<IsolationDetail> list = getBaseMapper().selectList(
        new LambdaQueryWrapper<IsolationDetail>()
            .eq(IsolationDetail::getRelId, relId)
            .orderByDesc(IsolationDetail::getCreateTime)
            .last("limit 3"));
    if(list.size() < 3){
      return;
    }
    for (IsolationDetail isolationDetail : list) {
      if(isolationDetail.getTemperature() >= IsolationDetailConstance.FEVER_TEMPERATURE){
        i++;
      }
    }
    //连续3天体温异常，直接转治疗,否则继续隔离中
    //todo 要不要一旦发烧，隔离结束时间重新开始计算14天
    IsolationPerson a = new IsolationPerson();
    if(i == IsolationDetailConstance.DEAL_DAYS){
      a.setState(IsolationPersonEnums.STATE_TREAT.getCode());
    }else {
      a.setState(IsolationPersonEnums.STATE_QUARANTINED.getCode());
    }
    isolationPersonMapper.update(a,
        new LambdaQueryWrapper<IsolationPerson>().eq(IsolationPerson::getId,relId));

  }
}
