package xyz.chnytrcy.campusepidemicsystem.controller.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.studentdaily.AddStudentDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.service.app.StudentDailyAppService;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.app
 * @ClassName: StudentDailyAppController
 * @Author: ChnyTrcy
 * @Description: 移动端 - 学生日常打卡接口
 * @Date: 2022/10/7 10:28
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/studentDaily")
@Api(value = "移动端 - 学生日常打卡接口",tags = "App - 学生日常打卡接口")
public class StudentDailyAppController {

  @Autowired private StudentDailyAppService studentDailyAppService;

  @PostMapping("/addStudentDaily")
  @ApiOperation("增加学生日常打卡记录")
  @RequiresPermissions(value = {"student:studentDaily:insert","isolate:studentDaily:insert"}
      ,logical = Logical.OR)
  public Result<Void> addStudentDaily(@RequestBody @Valid AddStudentDailyCommand command){
    return studentDailyAppService.addStudentDaily(command);
  }

}
