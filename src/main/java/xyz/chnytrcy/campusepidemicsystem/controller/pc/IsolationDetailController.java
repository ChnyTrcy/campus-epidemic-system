package xyz.chnytrcy.campusepidemicsystem.controller.pc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationdetail.AddIsolationDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationdetail.QueryIsolationDetailByIdCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationdetail.ReviseIsolationDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationdetail.QueryIsolationDetailByIdVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.IsolationDetailService;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.pc
 * @ClassName: IsolationDetailsController
 * @Author: ChnyTrcy
 * @Description: 隔离记录Controller
 * @Date: 2022/9/1 11:10 AM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/isolationDetail")
@Api(value = "隔离记录Controller",tags = "PC - 隔离记录接口")
public class IsolationDetailController {

  @Autowired private IsolationDetailService isolationDetailService;

  @GetMapping("/queryIsolationDetailById")
  @ApiOperation("根据隔离人员Id查询隔离记录")
  @RequiresPermissions("epidemic:isolationDetail:select")
  public Result<List<QueryIsolationDetailByIdVO>> queryIsolationDetailById(
      @Valid QueryIsolationDetailByIdCommand command){
    return isolationDetailService.queryIsolationDetailById(command);
  }

  @GetMapping("/addIsolationDetail")
  @ApiOperation("添加隔离人员体温监测")
  @RequiresPermissions("epidemic:isolationDetail:insert")
  public Result<Void> addIsolationDetail(@Valid AddIsolationDetailCommand command){
    return isolationDetailService.addIsolationDetail(command);
  }

  @GetMapping("/reviseIsolationDetail")
  @ApiOperation("修改某一条隔离体温记录")
  @RequiresPermissions("epidemic:isolationDetail:update")
  public Result<Void> reviseIsolationDetail(ReviseIsolationDetailCommand command){
    return isolationDetailService.reviseIsolationDetail(command);
  }


}
