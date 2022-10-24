package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.AddTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.DeleteTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.QueryEpidemicPersonCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.QueryTeacherPageCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.SetTeacherToEpidemicPersonCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.UpdateTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacher.QueryEpidemicPersonVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacher.QueryTeacherPageVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.TeacherService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.pc
 * @ClassName: TeacherController
 * @Author: ChnyTrcy
 * @Description: 教职工Controller
 * @Date: 2022/8/25 10:03 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/teacher")
@Api(value = "教职工Controller",tags = "PC - 教职工接口")
public class TeacherController {

  @Autowired private TeacherService teacherService;

  @PostMapping("/addTeacher")
  @ApiOperation("添加教职工")
  @RequiresPermissions("admin:teacher:insert")
  public Result<Void> addTeacher(@RequestBody @Valid AddTeacherCommand command){
    return teacherService.addTeacher(command);
  }

  @GetMapping("/queryTeacherPage")
  @ApiOperation("分页查询教职工")
  @RequiresPermissions("admin:teacher:select")
  public Result<BasePageVO<QueryTeacherPageVO>> queryTeacherPage(@Valid QueryTeacherPageCommand command){
    return teacherService.queryTeacherPage(command);
  }

  @PostMapping("/updateTeacher")
  @ApiOperation("修改教职工")
  @RequiresPermissions("admin:teacher:update")
  public Result<Void> updateTeacher(@Valid UpdateTeacherCommand command){
    return teacherService.updateTeacher(command);
  }

  @GetMapping("/deleteTeacher")
  @ApiOperation("删除教职工")
  @RequiresPermissions("admin:teacher:delete")
  public Result<Void> deleteTeacher(@Valid DeleteTeacherCommand command){
    return teacherService.deleteTeacher(command);
  }

  @GetMapping("/setTeacherToEpidemicPerson")
  @ApiOperation("切换教职工防疫人员属性")
  @RequiresPermissions("admin:teacher:update")
  public Result<Void> setTeacherToEpidemicPerson(@Valid SetTeacherToEpidemicPersonCommand command){
    return teacherService.setTeacherToEpidemicPerson(command);
  }

  @GetMapping("/queryEpidemicPerson")
  @ApiOperation("查询本院系防疫人员")
  @RequiresPermissions("admin:teacher:select")
  public Result<BasePageVO<QueryEpidemicPersonVO>>  queryEpidemicPerson(
      @Valid QueryEpidemicPersonCommand command){
    return teacherService.queryEpidemicPerson(command);
  }

}
