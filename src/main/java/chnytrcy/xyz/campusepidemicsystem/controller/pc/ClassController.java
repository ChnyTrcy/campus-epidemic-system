package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.classvo.ClassListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.ClassService;
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
 * @ClassName: ClassController
 * @Author: ChnyTrcy
 * @Description: 班级Controller
 * @Date: 2022/8/25 2:30 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/class")
@Api(value = "班级Controller",tags = "PC - 班级接口")
public class ClassController {

  @Autowired private ClassService classService;

  @GetMapping("/getClassListByMajorCode")
  @ApiOperation("根据专业编号获取班级列表")
  public Result<List<ClassListVO>> getClassListByMajorCode(){
    return classService.getClassListByMajorCode();
  }
}
