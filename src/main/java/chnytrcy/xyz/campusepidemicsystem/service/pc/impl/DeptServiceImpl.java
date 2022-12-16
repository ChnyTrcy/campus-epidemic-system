package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.DataSynchronous;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.ClassMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.DeptMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.dept.UpdateDeptCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.dept.DeptListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.DeptService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ExcelDealFactory;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo.DeptBO;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener.AnalysisBaseListener;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener.DeptListener;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcelFactory;
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

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
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

  @Override
  public Result<List<DeptListVO>> getDeptList() {
    List<Dept> deptList = getBaseMapper().selectList(null);
    List<DeptListVO> key = DozerUtils.convertList(deptList, DeptListVO.class);
    return ResultFactory.successResult(key);
  }

  @Override
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    InputStream resourceAsStream = this.getClass().getClassLoader()
        .getResourceAsStream("excelTemplates/deptTemplate.xlsx");
    OutputStream outputStream = response.getOutputStream();
    response.setContentType("application/x-download");
    response.addHeader("Content-Disposition", "attachment;filename=院系批量插入模版.xlsx");
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
