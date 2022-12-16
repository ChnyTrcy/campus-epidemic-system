package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.classvo.ClassListVO;
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
 * @InterfaceName: ClassService
 * @Author: ChnyTrcy
 * @Description: 班级服务接口
 * @Date: 2022/8/24 8:20 PM
 * @Version: 1.0
 */
public interface ClassService extends IService<ClassEntity> {

  /**
   * 根据专业编号获取班级列表
   */
  Result<List<ClassListVO>> getClassListByMajorCode();

  /**
   * 下载班级批量添加模版文件
   */
  void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException;

  /**
   * 上传并解析班级模版文件
   */
  Result uploadAndParseTemplate(MultipartFile file) throws IOException;
}
