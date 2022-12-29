package xyz.chnytrcy.campusepidemicsystem.service.pc;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.IsolationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageAdminIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageToBeIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.ReleaseQuarantineCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis.CountDeptPeopleProportionVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis.CountNewIsolationListVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis.CountPeopleDistributionVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryAdminIsolationPersonAnalysisVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageAdminIsolationPersonVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageIsolationPersonVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageToBeIsolationPersonVO;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service
 * @InterfaceName: IsolationPersonService
 * @Author: ChnyTrcy
 * @Description: 隔离人员服务接口
 * @Date: 2022/8/24 4:37 PM
 * @Version: 1.0
 */
public interface IsolationPersonService extends IService<IsolationPerson> {

  /**
   * 分页查询隔离人员
   */
  Result<BasePageVO<QueryPageAdminIsolationPersonVO>> queryPageAdminIsolationPerson(
      QueryPageAdminIsolationPersonCommand command);

  /**
   * 分页查询待隔离人员
   */
  Result<BasePageVO<QueryPageToBeIsolationPersonVO>> queryPageToBeIsolationPerson(
      QueryPageToBeIsolationPersonCommand command);

  /**
   * 防疫人员分页查询已隔离人员
   */
  Result<BasePageVO<QueryPageIsolationPersonVO>> queryPageIsolationPerson(
      QueryPageIsolationPersonCommand command);

  /**
   * 隔离该学生
   */
  Result<Void> isolation(IsolationCommand command);

  /**
   * 解除隔离
   */
  Result<Void> releaseQuarantine(ReleaseQuarantineCommand command);

  /**
   * 统计分析本院系的隔离人员状态分布
   */
  Result<QueryAdminIsolationPersonAnalysisVO> queryAdminIsolationPersonAnalysis();

  /**
   * 统计人数分布
   */
  Result<CountPeopleDistributionVO> countPeopleDistribution();

  /**
   * 各学院防疫人员和隔离人员配比
   */
  Result<CountDeptPeopleProportionVO> countDeptPeopleProportion();

  /**
   * 新增隔离人数趋势
   */
  Result<CountNewIsolationListVO> countNewIsolationList();
}
