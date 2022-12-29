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
import xyz.chnytrcy.campusepidemicsystem.model.command.app.teacherdaily.DownDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.teacherdaily.UpDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.service.app.TeacherDailyAppService;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.app
 * @ClassName: TeacherDailyAppController
 * @Author: ChnyTrcy
 * @Description: 教职工日常打卡Controller
 * @Date: 2022/9/6 11:19 AM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.app}/teacherDaily")
@Api(value = "教职工日常打卡Controller",tags = "App - 教职工打卡接口")
public class TeacherDailyAppController {

  @Autowired private TeacherDailyAppService teacherDailyAppService;

  @GetMapping("/upDaily")
  @ApiOperation("教职工/防疫人员上班打卡")
  @RequiresPermissions(value = {"teacher:teacherDaily:insert","epidemic:teacherDaily:insert"}
      ,logical = Logical.OR)
  public Result<Void> upDaily(@Valid UpDailyCommand command){
    return teacherDailyAppService.upDaily(command);
  }

  @GetMapping("/downDaily")
  @ApiOperation("教职工/防疫人员下班打卡")
  @RequiresPermissions(value = {"teacher:teacherDaily:insert","epidemic:teacherDaily:insert"},
      logical = Logical.OR)
  public Result<Void> downDaily(@Valid DownDailyCommand command){
    return teacherDailyAppService.downDaily(command);
  }
}
