package xyz.chnytrcy.campusepidemicsystem.controller.pc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.classvo.ClassListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.ClassService;
import xyz.chnytrcy.core.config.annotation.RateLimitAnnotation;
import xyz.chnytrcy.core.utils.result.Result;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.controller.pc
 * @ClassName: ClassController
 * @Author: ChnyTrcy
 * @Description: 班级Controller
 * @Date: 2022/8/25 2:30 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/class")
@Api(value = "班级Controller",tags = "PC - 班级接口")
public class ClassController {

  @Autowired private ClassService classService;

  @GetMapping("/getClassListByMajorCode")
  @ApiOperation("根据专业编号获取班级列表")
  @RateLimitAnnotation(value = 10)
  public Result<List<ClassListVO>> getClassListByMajorCode(){
    return classService.getClassListByMajorCode();
  }

  @GetMapping("/downloadTemplate")
  @RequiresPermissions("admin:class:insert")
  @ApiOperation("下载班级批量添加模版文件")
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    classService.downloadTemplate(request,response);
  }

  @PostMapping("/uploadAndParseTemplate")
  @RequiresPermissions("admin:class:insert")
  @ApiOperation("上传并解析班级模版文件")
  public Result uploadAndParseTemplate(MultipartFile file) throws IOException {
    return classService.uploadAndParseTemplate(file);
  }
}
