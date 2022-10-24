package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacherdaily.QueryTeacherDailyHistoryCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacherdaily.QueryTeacherDailyHistoryVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.TeacherDailyService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.pc
 * @ClassName: TeacherDailyController
 * @Author: ChnyTrcy
 * @Description: 教职工日常打卡Controller
 * @Date: 2022/9/6 9:51 AM
 * @Version: 1.0
 */
@Api(value = "教职工日常打卡Controller",tags = "PC - 教职工打卡接口")
@RestController
@RequestMapping("${mvc.url.perfix.pc}/teacherDaily")
public class TeacherDailyController {

  @Autowired private TeacherDailyService teacherDailyService;

  @RequiresPermissions("admin:teacherDaily:select")
  @GetMapping("/queryTeacherDailyHistory")
  @ApiOperation("查看10天该教职工的历史打卡记录")
  public Result<List<QueryTeacherDailyHistoryVO>> queryTeacherDailyHistory(
      @Valid QueryTeacherDailyHistoryCommand command){
    return teacherDailyService.queryTeacherDailyHistory(command);
  }
}
