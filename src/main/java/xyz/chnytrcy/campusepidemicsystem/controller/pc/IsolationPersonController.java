package xyz.chnytrcy.campusepidemicsystem.controller.pc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.IsolationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageAdminIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageToBeIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.ReleaseQuarantineCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryAdminIsolationPersonAnalysisVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageAdminIsolationPersonVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageIsolationPersonVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageToBeIsolationPersonVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.IsolationPersonService;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.pc
 * @ClassName: IsolationPersonController
 * @Author: ChnyTrcy
 * @Description: 隔离人员Controller
 * @Date: 2022/8/30 9:38 AM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/isolationPerson")
@Api(value = "隔离人员Controller",tags = "PC - 隔离人员接口")
public class IsolationPersonController {

  @Autowired private IsolationPersonService isolationPersonService;

  @GetMapping("/queryPageAdminIsolationPerson")
  @RequiresPermissions("admin:isolationPerson:select")
  @ApiOperation("管理员分页查询隔离人员")
  public Result<BasePageVO<QueryPageAdminIsolationPersonVO>> queryPageAdminIsolationPerson(
      @Valid QueryPageAdminIsolationPersonCommand command){
    return isolationPersonService.queryPageAdminIsolationPerson(command);
  }

  @GetMapping("/queryPageToBeIsolationPerson")
  @RequiresPermissions("epidemic:isolationPerson:select")
  @ApiOperation("分页查询待隔离人员")
  public Result<BasePageVO<QueryPageToBeIsolationPersonVO>> queryPageToBeIsolationPerson(
      @Valid QueryPageToBeIsolationPersonCommand command){
    return isolationPersonService.queryPageToBeIsolationPerson(command);
  }

  @GetMapping("/isolation")
  @RequiresPermissions("epidemic:isolationPerson:update")
  @ApiOperation("隔离该学生")
  public Result<Void> isolation(@Valid IsolationCommand command){
    return isolationPersonService.isolation(command);
  }

  @GetMapping("/queryPageIsolationPerson")
  @RequiresPermissions("epidemic:isolationPerson:select")
  @ApiOperation("分页查询隔离人员")
  public Result<BasePageVO<QueryPageIsolationPersonVO>> queryPageIsolationPerson(
      @Valid QueryPageIsolationPersonCommand command){
    return isolationPersonService.queryPageIsolationPerson(command);
  }

  @GetMapping("/releaseQuarantine")
  @RequiresPermissions("epidemic:isolationPerson:update")
  @ApiOperation("解除隔离")
  public Result<Void> releaseQuarantine(@Valid ReleaseQuarantineCommand command){
    return isolationPersonService.releaseQuarantine(command);
  }

  @GetMapping("/queryEpidemicIsolationPersonAnalysis")
  @RequiresPermissions("epidemic:isolationPerson:select")
  @ApiOperation("统计分析本院系的隔离人员状态分布")
  public Result<QueryAdminIsolationPersonAnalysisVO> queryAdminIsolationPersonAnalysis(){
    return isolationPersonService.queryAdminIsolationPersonAnalysis();
  }

}
