package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.mapper.MajorMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.MajorListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.MajorService;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ExcelDealFactory;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener.AnalysisBaseListener;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: MajorServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 8:27 PM
 * @Version: 1.0
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

  @Autowired private ExcelDealFactory excelDealFactory;

  @Override
  public Result<List<MajorListVO>> getMajorListByDept() {
    List<MajorListVO> key = getBaseMapper().getMajorListByDept();
    return ResultFactory.successResult(key);
  }

  @Override
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    InputStream resourceAsStream = this.getClass().getClassLoader()
        .getResourceAsStream("excelTemplates/majorTemplate.xlsx");
    OutputStream outputStream = response.getOutputStream();
    response.setContentType("application/x-download");
    response.addHeader("Content-Disposition", "attachment;filename=专业批量插入模版.xlsx");
    IOUtils.copy(resourceAsStream, outputStream);
    outputStream.flush();
  }

  @Override
  public Result uploadAndParseTemplate(MultipartFile file) throws IOException {
    AnalysisBaseListener instance = excelDealFactory.getInstance(EntityEnums.MAJOR);
    return excelDealFactory.dealMain(instance,file);
  }
}
