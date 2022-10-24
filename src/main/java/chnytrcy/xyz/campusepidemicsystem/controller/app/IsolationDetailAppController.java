package chnytrcy.xyz.campusepidemicsystem.controller.app;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail.AddTemperatureCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail.QueryPageIsolationDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationdetail.AddTemperatureVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationdetail.QueryPageIsolationDetailVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.IsolationDetailAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.app
 * @ClassName: IsolationDetailAppController
 * @Author: ChnyTrcy
 * @Description: 移动端 - 隔离记录Controller
 * @Date: 2022/9/13 3:25 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/isolationDetail")
@Api(value = "移动端 - 隔离记录Controller",tags = "App - 隔离记录接口")
public class IsolationDetailAppController {

  @Autowired private IsolationDetailAppService isolationDetailAppService;

  @ApiOperation("添加隔离人员体温")
  @RequiresPermissions("epidemic:isolationDetail:insert")
  @GetMapping("/addTemperature")
  public Result<AddTemperatureVO> addTemperature(AddTemperatureCommand command){
    return isolationDetailAppService.addTemperature(command);
  }

  @ApiOperation("分页查询隔离人员隔离记录")
  @RequiresPermissions("isolation:isolationDetail:select")
  @GetMapping("/queryPageIsolationDetail")
  public Result<BasePageVO<QueryPageIsolationDetailVO>> queryPageIsolationDetail(
      QueryPageIsolationDetailCommand command){
    return isolationDetailAppService.queryPageIsolationDetail(command);
  }

}
