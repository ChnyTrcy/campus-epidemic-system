package xyz.chnytrcy.campusepidemicsystem.controller.app;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.AddTemperatureCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.InsertDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.QueryPageIsolationDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.AddTemperatureVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.QueryIsolationTimeVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.QueryPageIsolationDetailVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.IsolationDetailAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.app
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

  @ApiOperation("添加每日健康打卡记录")
  @RequiresPermissions("isolation:isolationDetail:insert")
  @PostMapping("/insertDetail")
  public Result<Void> insertDetail(@Valid @RequestBody InsertDetailCommand command){
    return isolationDetailAppService.insertDetail(command);
  }

  @ApiOperation("获取隔离开始/结束时间")
  @RequiresPermissions("isolation:isolationDetail:select")
  @GetMapping("/queryIsolationTime")
  public Result<QueryIsolationTimeVO> queryIsolationTime(){
    return isolationDetailAppService.queryIsolationTime();
  }

}
