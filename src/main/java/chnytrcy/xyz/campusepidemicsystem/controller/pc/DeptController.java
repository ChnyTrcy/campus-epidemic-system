package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.RateLimitAnnotation;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.dept.DeptListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.DeptService;
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
 * @ClassName: DeptController
 * @Author: ChnyTrcy
 * @Description: 院系Controller
 * @Date: 2022/8/25 2:05 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/dept")
@Api(value = "院系Controller",tags = "PC - 院系接口")
public class DeptController {

  @Autowired private DeptService deptService;

  @GetMapping("/getDeptList")
  @ApiOperation("获取院系列表")
  @RateLimitAnnotation(value = 2)
  public Result<List<DeptListVO>> getDeptList(){
    return deptService.getDeptList();
  }
}
