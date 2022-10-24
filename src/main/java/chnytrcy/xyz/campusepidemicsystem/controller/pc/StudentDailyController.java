package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.command.pc.studentdalily.QueryStudentDailyCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.studentdaily.QueryStudentDailyVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StudentDailyService;
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
