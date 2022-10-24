package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.province.GetProvinceListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.ProvinceService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.pc
 * @ClassName: ProvinceController
 * @Author: ChnyTrcy
 * @Description: 省份Controller
 * @Date: 2022/8/28 5:29 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/province")
@Api(value = "省份Controller",tags = "PC - 省份接口")
public class ProvinceController {

  @Autowired private ProvinceService provinceService;

  @GetMapping("/getProvinceList")
  @ApiOperation("获取省份列表")
  public Result<List<GetProvinceListVO>> getProvinceList(){
    return provinceService.getProvinceList();
  }
}
