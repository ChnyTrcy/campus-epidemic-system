package chnytrcy.xyz.campusepidemicsystem.controller.app;

import chnytrcy.xyz.campusepidemicsystem.model.vo.app.teacher.GetInformationVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.TeacherAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.app
 * @ClassName: TeacherAppController
 * @Author: ChnyTrcy
 * @Description: 移动端 - 教职工Controller
 * @Date: 2022/9/13 5:09 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/teacher")
@Api(value = "移动端 - 教职工Controller",tags = "App - 教职工接口")
public class TeacherAppController {

  @Autowired private TeacherAppService teacherAppService;

  @GetMapping("/getInformation")
  @ApiOperation("获得教职工/防疫人员信息")
  @RequiresPermissions({"teacher:teacher:select","epidemic:teacher:select"})
  public Result<GetInformationVO> getInformation(){
    return teacherAppService.getInformation();
  }

}
