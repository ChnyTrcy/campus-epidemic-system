package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.chnytrcy.campusepidemicsystem.common.TeacherCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationDetailMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationPersonMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationperson.IsolationOperationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationperson.LiftIsolationOperationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.dto.QueryPageIsolationPersonByEpidemicDTO;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationperson.QueryPageIsolationPersonByEpidemicVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.IsolationPersonAppService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.impl.IsolationPersonServiceImpl;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
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
