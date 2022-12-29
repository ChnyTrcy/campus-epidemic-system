package xyz.chnytrcy.campusepidemicsystem.controller.pc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.city.GetCityListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.city.GetCityListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.CityService;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.pc
 * @ClassName: CityController
 * @Author: ChnyTrcy
 * @Description: 城市Controller
 * @Date: 2022/8/28 5:38 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/city")
@Api(value = "城市Controller",tags = "PC - 城市接口")
public class CityController {

  @Autowired private CityService cityService;

  @GetMapping("/getCityList")
  @ApiOperation("获得城市列表")
  public Result<List<GetCityListVO>> getCityList(@Valid GetCityListCommand command){
    return cityService.getCityList(command);
  }
}
