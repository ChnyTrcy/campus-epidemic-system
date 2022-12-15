package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.dept.DeptListVO;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc
 * @InterfaceName: DeptService
 * @Author: ChnyTrcy
 * @Description: 院系服务接口
 * @Date: 2022/8/24 8:23 PM
 * @Version: 1.0
 */
public interface DeptService extends IService<Dept> {

  /**
   * 获取院系列表
   */
  Result<List<DeptListVO>> getDeptList();

  /**
   * 下载院系批量添加模版文件
   * @param request
   * @param response
   */
  void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException;

  /**
   * 上传并解析院系模版文件
   * @param file
   * @return
   */
  Result uploadAndParseTemplate(MultipartFile file) throws IOException;
}
