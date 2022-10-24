package chnytrcy.xyz.campusepidemicsystem.controller.app;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageCommand;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationperson.IsolationOperationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationperson.LiftIsolationOperationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.dto.QueryPageIsolationPersonByEpidemicDTO;
import chnytrcy.xyz.campusepidemicsystem.service.app.IsolationPersonAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.app
 * @ClassName: IsolationPersonAppController
 * @Author: ChnyTrcy
 * @Description: 移动端隔离人员Controller
 * @Date: 2022/9/10 4:37 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/isolationPerson")
@Api(value = "移动端隔离人员Controller",tags = "App - 隔离人员接口")
public class IsolationPersonAppController {

  @Autowired private IsolationPersonAppService isolationPersonAppService;

  @GetMapping("/queryPageIsolationPersonByEpidemic")
  @RequiresPermissions("epidemic:isolationPerson:select")
  @ApiOperation("防疫人员分页查询对应的隔离人员反馈表")
  public Result<BasePageVO<QueryPageIsolationPersonByEpidemicDTO>> queryPageIsolationPersonByEpidemic(
      @Valid BasePageCommand command){
    return isolationPersonAppService.queryPageIsolationPersonByEpidemic(command);
  }

  @GetMapping("/isolationOperation")
  @RequiresPermissions("epidemic:isolationPerson:update")
  @ApiOperation("对待隔离人员进行隔离操作")
  public Result<Void> isolationOperation(@Valid IsolationOperationCommand command){
    return isolationPersonAppService.isolationOperation(command);
  }

  @GetMapping("/liftIsolationOperation")
  @RequiresPermissions("epidemic:isolationPerson:update")
  @ApiOperation("对隔离人员进行解除隔离操作")
  public Result<Void> liftIsolationOperation(@Valid LiftIsolationOperationCommand command){
    return isolationPersonAppService.liftIsolationOperation(command);
  }

}
