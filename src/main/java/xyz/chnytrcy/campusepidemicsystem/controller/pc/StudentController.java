package xyz.chnytrcy.campusepidemicsystem.controller.pc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.student.AddStudentCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.student.DeleteStudentCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.student.QueryStudentByKeywordCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.student.UpdateStudentCommand;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.student.QueryStudentByKeywordVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.StudentService;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.pc
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

  @GetMapping("/downloadTemplate")
  @RequiresPermissions("admin:student:insert")
  @ApiOperation("下载学生批量添加模版文件")
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
    studentService.downloadTemplate(request,response);
  }

  @PostMapping("/uploadAndParseTemplate")
  @RequiresPermissions("admin:student:insert")
  @ApiOperation("上传并解析学生模板文件")
  public Result uploadAndParseTemplate(MultipartFile file) throws IOException {
    return studentService.uploadAndParseTemplate(file);
  }

}
