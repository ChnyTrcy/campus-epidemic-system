package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.major.GetMajorListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.GetMajorListVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.MajorListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.MajorService;
import chnytrcy.xyz.campusepidemicsystem.utils.aop.datasynchronization.handler.BaseDeal;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.pc
 * @ClassName: MajorController
 * @Author: ChnyTrcy
 * @Description: 专业Controller
 * @Date: 2022/8/25 2:08 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix.pc}/major")
@Api(value = "专业Controller",tags = "PC - 专业接口")
public class MajorController {

  @Autowired private MajorService majorService;

  @GetMapping("/getMajorListByDept")
  @ApiOperation("根据院系编号获取对应的专业列表")
  public Result<List<MajorListVO>> getMajorListByDept(){
    return majorService.getMajorListByDept();
  }

  @GetMapping("/downloadTemplate")
  @RequiresPermissions("admin:major:insert")
  @ApiOperation("下载专业批量添加模版文件")
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    majorService.downloadTemplate(request,response);
  }

  @PostMapping("/uploadAndParseTemplate")
  @RequiresPermissions("admin:major:insert")
  @ApiOperation("上传并解析专业模版文件")
  public Result uploadAndParseTemplate(MultipartFile file) throws IOException {
    return majorService.uploadAndParseTemplate(file);
  }

  @GetMapping("/getMajorList")
  @ApiOperation("获得专业列表")
  @RequiresPermissions("admin:major:select")
  public Result<BasePageVO<GetMajorListVO>> getMajorList(@Valid GetMajorListCommand command){
    return majorService.getMajorList(command);
  }
}
