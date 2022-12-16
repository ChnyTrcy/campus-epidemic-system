package chnytrcy.xyz.campusepidemicsystem.service.pc;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.major.GetMajorListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.GetMajorListVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.MajorListVO;
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
 * @InterfaceName: MajorService
 * @Author: ChnyTrcy
 * @Description: 专业服务接口
 * @Date: 2022/8/24 8:27 PM
 * @Version: 1.0
 */
public interface MajorService extends IService<Major> {

  /**
   * 根据院系编号获取对应的专业列表
   */
  Result<List<MajorListVO>> getMajorListByDept();

  /**
   * 下载专业批量添加模版文件
   */
  void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException;

  /**
   * 上传并解析专业模版文件
   */
  Result uploadAndParseTemplate(MultipartFile file) throws IOException;

  /**
   * 获得专业列表
   */
  Result<BasePageVO<GetMajorListVO>> getMajorList(GetMajorListCommand command);
}
