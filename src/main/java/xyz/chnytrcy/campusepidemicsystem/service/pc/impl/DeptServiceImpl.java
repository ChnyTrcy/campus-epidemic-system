package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import xyz.chnytrcy.campusepidemicsystem.converter.DeptConverter;
import xyz.chnytrcy.campusepidemicsystem.mapper.ClassMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.DeptMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.dept.UpdateDeptCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Dept;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.dept.DeptListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.DeptService;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.DataSynchronous;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.ExcelDealFactory;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.AnalysisBaseListener;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
 * @ClassName: DeptServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 8:23 PM
 * @Version: 1.0
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

  @Autowired private ExcelDealFactory excelDealFactory;

  @Autowired private ClassMapper classMapper;

  @Autowired private DeptConverter deptConverter;

  @Override
  public Result<List<DeptListVO>> getDeptList() {
    List<Dept> deptList = getBaseMapper().selectList(null);
    return ResultFactory.successResult(deptConverter.deptEntityToDeptListVo(deptList));
  }

  @Override
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    InputStream resourceAsStream = this.getClass().getClassLoader()
        .getResourceAsStream("excelTemplates/deptTemplate.xlsx");
    OutputStream outputStream = response.getOutputStream();
    response.setContentType("application/x-download");
    response.addHeader("Content-Disposition", "attachment;filename=template.xlsx");
    IOUtils.copy(resourceAsStream, outputStream);
    outputStream.flush();
  }

  @Override
  public Result uploadAndParseTemplate(MultipartFile file) throws IOException {
    AnalysisBaseListener instance = excelDealFactory.getInstance(EntityEnums.DEPT);
    return excelDealFactory.dealMain(instance,file);
  }

  @Override
  @DataSynchronous(type = EntityEnums.DEPT)
  public Result<Void> updateDeptName(UpdateDeptCommand command) {
    Dept dept = getBaseMapper().selectOne(
        new LambdaQueryWrapper<Dept>().eq(Dept::getId, command.getId()));
    if(ObjectUtil.isNull(dept)){
      throw new BusinessException(BusinessError.DEPT_NOT_EXIST_ERROR);
    }
    if(command.getName().equals(dept.getName())){
      throw new BusinessException(BusinessError.DEPT_NAME_EXIST_ERROR);
    }
    Dept convert = DozerUtils.convert(command, Dept.class);
    getBaseMapper().updateById(convert);
    return ResultFactory.successResult();
  }

}
