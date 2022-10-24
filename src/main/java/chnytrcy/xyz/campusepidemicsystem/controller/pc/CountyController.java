package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.county.GetCountyListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.county.GetCountyListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.CountyService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.pc
 * @ClassName: CountyController
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/28 5:47 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/county")
@Api(value = "区县Controller",tags = "PC - 区县接口")
public class CountyController {

  @Autowired private CountyService countyService;

  @ApiOperation("根据城市编号获得区县列表")
  @GetMapping("/getCountyList")
  public Result<List<GetCountyListVO>> getCountyList(@Valid GetCountyListCommand command){
    return countyService.getCountyList(command);
  }
}
