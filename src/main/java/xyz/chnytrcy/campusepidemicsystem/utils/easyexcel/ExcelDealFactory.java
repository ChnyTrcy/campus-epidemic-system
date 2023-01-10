package xyz.chnytrcy.campusepidemicsystem.utils.easyexcel;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcelFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.AnalysisBaseListener;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.ClassListener;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.DeptListener;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.MajorListener;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.StudentListener;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.TeacherListener;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.easyexcel
 * @ClassName: ExcelDealFactory
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/15 18:04
 * @Version: 1.0
 */
@Component
public class ExcelDealFactory {

  @Autowired private DeptListener deptListener;

  @Autowired private MajorListener majorListener;

  @Autowired private ClassListener classListener;

  @Autowired private StudentListener studentListener;

  @Autowired private TeacherListener teacherListener;

  public AnalysisBaseListener getInstance(EntityEnums enums){
    switch (enums){
      case DEPT:
        return deptListener;
      case MAJOR:
        return majorListener;
      case CLASS_ENTITY:
        return classListener;
      case STUDENT:
        return studentListener;
      case TEACHER:
        return teacherListener;
      default:
        throw new BusinessException(BusinessError.ENTITY_ENUMS_NOT_EXIST_ERROR);
    }
  }

  public Result dealMain(AnalysisBaseListener listener,MultipartFile file) throws IOException {
    InputStream inputStream = file.getInputStream();
    EasyExcelFactory
        .read(inputStream, listener.getT(),listener)
        .sheet(0)
        .headRowNumber(2)
        .doReadSync();
    List<ErrorEntity> errorList = listener.getErrorList();
    if(CollUtil.isEmpty(errorList)){
      return ResultFactory.successResult();
    }else {
      return ResultFactory.warningResult(errorList);
    }
  }

}
