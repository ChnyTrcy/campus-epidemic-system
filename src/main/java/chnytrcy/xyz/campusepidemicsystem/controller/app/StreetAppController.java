package chnytrcy.xyz.campusepidemicsystem.controller.app;

import chnytrcy.xyz.campusepidemicsystem.model.command.app.street.QueryStreetListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.street.QueryStreetListVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.StreetAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.app
 * @ClassName: StreetAppController
 * @Author: ChnyTrcy
 * @Description: 移动端 - 街道模块Controller
 * @Date: 2022/9/12 2:01 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/street")
@Api(value = "移动端 - 街道模块Controller",tags = "App - 街道接口")
public class StreetAppController {

  @Autowired private StreetAppService streetAppService;

  @ApiOperation("查询街道列表")
  @GetMapping("/queryStreetList")
  @RequiresPermissions("student:street:select")
  public Result<List<QueryStreetListVO>> queryStreetList(@Valid QueryStreetListCommand command){
    return streetAppService.queryStreetList(command);
  }

}
