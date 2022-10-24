package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import chnytrcy.xyz.campusepidemicsystem.common.TeacherCommon;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageCommand;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationPersonMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationperson.IsolationOperationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationperson.LiftIsolationOperationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.constance.IsolationDetailConstance;
import chnytrcy.xyz.campusepidemicsystem.model.constance.IsolationPersonConstance;
import chnytrcy.xyz.campusepidemicsystem.model.dto.QueryPageIsolationPersonByEpidemicDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationDetail;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationperson.QueryPageIsolationPersonByEpidemicVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.IsolationPersonAppService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.IsolationPersonService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.impl.IsolationPersonServiceImpl;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
 * @ClassName: IsolationPersonAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/10 4:39 PM
 * @Version: 1.0
 */
@Service
public class IsolationPersonAppServiceImpl extends ServiceImpl<IsolationPersonMapper,IsolationPerson>
    implements IsolationPersonAppService {

  @Autowired private IsolationDetailMapper isolationDetailMapper;

  @Autowired private IsolationPersonServiceImpl isolationPersonService;

  @Autowired private TeacherCommon teacherCommon;

  @Override
  public Result<BasePageVO<QueryPageIsolationPersonByEpidemicDTO>> queryPageIsolationPersonByEpidemic(
      BasePageCommand command) {
    Teacher epidemic = teacherCommon.getEpidemic();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<QueryPageIsolationPersonByEpidemicDTO> list = getBaseMapper().queryPageIsolationPersonByEpidemic(
        epidemic.getCode());
    PageInfo pageInfo = new PageInfo(list);
    List<QueryPageIsolationPersonByEpidemicVO> vos = DozerUtils.convertList(
        list, QueryPageIsolationPersonByEpidemicVO.class);
    pageInfo.setList(vos);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> isolationOperation(IsolationOperationCommand command) {
    Teacher epidemic = teacherCommon.getEpidemic();
    IsolationPerson isolationPerson = getBaseMapper().selectOne(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getRelId, command.getId())
            .eq(IsolationPerson::getDeptCode, epidemic.getDeptCode()));
    isolationPersonService.isolationDeal(isolationPerson,epidemic);
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> liftIsolationOperation(LiftIsolationOperationCommand command) {
    return isolationPersonService.releaseQuarantine(command);
  }
}
