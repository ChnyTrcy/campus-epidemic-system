package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.street.BatchModifyStreetRiskLevelCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.street.ModifyStreetRiskLevelCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.street.QueryPageStreetCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.street.QueryPageStreetVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.street.QueryStatisticsStreetVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StreetService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.pc
 * @ClassName: StreetController
 * @Author: ChnyTrcy
 * @Description: 街区Controller
 * @Date: 2022/8/28 7:33 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/street")
@Api(value = "街区Controller",tags = "PC - 街区接口")
public class StreetController {

  @Autowired private StreetService streetService;

  @GetMapping("/queryPageStreet")
  @RequiresPermissions("admin:street:select")
  @ApiOperation(value = "分页查询街道")
  public Result<BasePageVO<QueryPageStreetVO>> queryPageStreet(@Valid QueryPageStreetCommand command){
    return streetService.queryPageStreet(command);
  }

  @GetMapping("/modifyStreetRiskLevel")
  @RequiresPermissions("admin:street:update")
  @ApiOperation(value = "修改地区的风险等级")
  public Result<Void> modifyStreetRiskLevel(@Valid ModifyStreetRiskLevelCommand command){
    return streetService.modifyStreetRiskLevel(command);
  }

  @PostMapping("/batchModifyStreetRiskLevel")
  @RequiresPermissions("admin:street:update")
  @ApiOperation(value = "批量修改地区的风险等级")
  public Result<Void> batchModifyStreetRiskLevel(@RequestBody @Valid BatchModifyStreetRiskLevelCommand command){
    return streetService.batchModifyStreetRiskLevel(command);
  }

  @GetMapping("/queryStatisticsStreet")
  @RequiresPermissions("admin:street:select")
  @ApiOperation("统计各风险地区数量")
  public Result<QueryStatisticsStreetVO> queryStatisticsStreet(){
    return streetService.queryStatisticsStreet();
  }
}
