package xyz.chnytrcy.campusepidemicsystem.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.student.ScanCodeGetStudentInformationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.student.GetUserIdVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.student.ScanCodeGetStudentInformationVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.StudentAppService;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.app
 * @ClassName: StudentAppController
 * @Author: ChnyTrcy
 * @Description: 移动端 - 学生接口
 * @Date: 2022/9/12 2:41 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/student")
@Api(value = "移动端 - 学生接口",tags = "App - 学生接口")
public class StudentAppController {

  @Autowired private StudentAppService studentAppService;

  @GetMapping("/getUserId")
  @ApiOperation("获取学生ID")
  @RequiresPermissions(value = {"student:student:select","isolation:student:select"},logical = Logical.OR)
  public Result<GetUserIdVO> getUserId(){
    return studentAppService.getUserId();
  }

  @GetMapping("/scanCodeGetStudentInformation")
  @ApiOperation("扫码获取学生的信息以及操作权限")
  @RequiresPermissions("epidemic:student:select")
  public Result<ScanCodeGetStudentInformationVO> scanCodeGetStudentInformation(
      @Valid ScanCodeGetStudentInformationCommand command){
    return studentAppService.scanCodeGetStudentInformation(command);
  }

}
