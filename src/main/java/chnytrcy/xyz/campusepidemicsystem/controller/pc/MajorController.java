package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.MajorListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.MajorService;
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
 * @ClassName: MajorController
 * @Author: ChnyTrcy
 * @Description: 专业Controller
 * @Date: 2022/8/25 2:08 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/major")
@Api(value = "专业Controller",tags = "PC - 专业接口")
public class MajorController {

  @Autowired private MajorService majorService;

  @GetMapping("/getMajorListByDept")
  @ApiOperation("根据院系编号获取对应的专业列表")
  public Result<List<MajorListVO>> getMajorListByDept(){
    return majorService.getMajorListByDept();
  }

}
