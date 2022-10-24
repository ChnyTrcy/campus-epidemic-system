package chnytrcy.xyz.campusepidemicsystem.controller.pc;

import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.menu.MenuBaseVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.MenuService;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.controller.pc
 * @ClassName: SystemController
 * @Author: ChnyTrcy
 * @Description: 系统请求
 * @Date: 2022/8/23 11:21 PM
 * @Version: 1.0
 */
@RestController
@RequestMapping("${mvc.url.perfix}/pc/system")
@Api(value = "菜单Controller",tags = "PC - 菜单接口")
public class SystemController {

  @Autowired private MinioClient minioClient;

  @Autowired
  private MenuService menuService;

  @ApiOperation("获取菜单列表")
  @GetMapping("/menu")
  public Result<List<MenuBaseVO>> menu(){
    return menuService.menuList();
  }

  @PostMapping("/upload")
  public Result upload(MultipartFile file)
      throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
    PutObjectArgs objectArgs = PutObjectArgs.builder().object(file.getOriginalFilename())
        .bucket("test")
        .contentType(file.getContentType())
        .stream(file.getInputStream(), file.getSize(), -1)
        .build();
    minioClient.putObject(objectArgs);
    return ResultFactory.successResult();
  }

  @GetMapping("/download")
  public Result<Void> download(HttpServletResponse response)
      throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
    InputStream inputStream = minioClient.getObject(GetObjectArgs
        .builder()
        .bucket("test")
        .object("student.sql")
        .build());
    ServletOutputStream outputStream = response.getOutputStream();
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-Disposition", "attachment; filename=student.sql");
    response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
    response.setHeader("Pragma", "public");
    response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
    IOUtils.copy(inputStream, outputStream);
    return ResultFactory.successResult();
  }
}
