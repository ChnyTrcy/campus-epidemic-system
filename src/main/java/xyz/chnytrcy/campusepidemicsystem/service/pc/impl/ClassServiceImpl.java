package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

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
import xyz.chnytrcy.campusepidemicsystem.mapper.ClassMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.ClassEntity;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.classvo.ClassListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.ClassService;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.ExcelDealFactory;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.AnalysisBaseListener;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
 * @ClassName: ClassServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 8:20 PM
 * @Version: 1.0
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, ClassEntity> implements ClassService {

  @Autowired private ExcelDealFactory excelDealFactory;

  @Override
  public Result<List<ClassListVO>> getClassListByMajorCode() {
    List<ClassListVO> key = getBaseMapper().getClassListByMajorCode();
    return ResultFactory.successResult(key);
  }

  @Override
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    InputStream resourceAsStream = this.getClass().getClassLoader()
        .getResourceAsStream("excelTemplates/classTemplate.xlsx");
    OutputStream outputStream = response.getOutputStream();
    response.setContentType("application/x-download");
    response.addHeader("Content-Disposition", "attachment;filename=template.xlsx");
    IOUtils.copy(resourceAsStream, outputStream);
    outputStream.flush();
  }

  @Override
  public Result uploadAndParseTemplate(MultipartFile file) throws IOException {
    AnalysisBaseListener instance = excelDealFactory.getInstance(EntityEnums.CLASS_ENTITY);
    return excelDealFactory.dealMain(instance,file);
  }
}
