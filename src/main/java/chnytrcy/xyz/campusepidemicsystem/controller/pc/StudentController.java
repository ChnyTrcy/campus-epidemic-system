package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.AddStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.DeleteStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.QueryStudentByKeywordCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.UpdateStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.student.QueryStudentByKeywordVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StudentService;
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
 * @ClassName: StudentController
 * @Author: ChnyTrcy
 * @Description: 学生Controller
 * @Date: 2022/8/24 3:01 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/student")
@Api(value = "学生Controller",tags = "PC - 学生接口")
public class StudentController {

  @Autowired private StudentService studentService;

  @PostMapping("/addStudent")
  @RequiresPermissions("admin:student:insert")
  @ApiOperation("增加单个学生")
  public Result<Void> addStudent(@RequestBody @Valid AddStudentCommand command){
    return studentService.addStudent(command);
  }

  @GetMapping("/queryStudentByKeyword")
  @RequiresPermissions("admin:student:select")
  @ApiOperation("关键词分页查询学号接口")
  public Result<BasePageVO<QueryStudentByKeywordVO>> queryStudentByKeyword(@Valid QueryStudentByKeywordCommand command){
    return studentService.queryStudentByKeyword(command);
  }

  @PostMapping("/updateStudent")
  @RequiresPermissions("admin:student:update")
  @ApiOperation("修改学生信息")
  public Result<Void> updateStudent(@RequestBody @Valid UpdateStudentCommand command){
    return studentService.updateStudent(command);
  }

  @GetMapping("/deleteStudent")
  @RequiresPermissions("admin:student:delete")
  @ApiOperation("根据学号删除学生")
  public Result<Void> deleteStudent(@Valid DeleteStudentCommand command){
    return studentService.deleteStudent(command);
  }

}
