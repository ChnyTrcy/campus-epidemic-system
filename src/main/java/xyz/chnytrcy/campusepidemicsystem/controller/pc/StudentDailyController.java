package xyz.chnytrcy.campusepidemicsystem.controller.pc;

import xyz.chnytrcy.campusepidemicsystem.model.command.pc.studentdalily.QueryStudentDailyCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.studentdaily.QueryStudentDailyVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.StudentDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.pc
 * @ClassName: StudentDailyController
 * @Author: ChnyTrcy
 * @Description: 学生日常打卡Controller
 * @Date: 2022/10/7 13:33
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/studentDaily")
@Api(value = "学生日常打卡Controller",tags = "PC - 学生日常打卡接口")
public class StudentDailyController {

  @Autowired private StudentDailyService studentDailyService;

  @GetMapping("/queryStudentDaily")
  @RequiresPermissions("admin:studentDaily:select")
  @ApiOperation("查询学生日常打卡记录列表")
  public Result<List<QueryStudentDailyVO>> queryStudentDaily(@Valid QueryStudentDailyCommand command){
    return studentDailyService.queryStudentDaily(command);
  }

}
