package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import xyz.chnytrcy.campusepidemicsystem.mapper.MajorMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.major.GetMajorListCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.major.UpdateMajorNameCommand;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Major;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.EntityEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.major.GetMajorListVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.major.MajorListVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.MajorService;
import xyz.chnytrcy.campusepidemicsystem.utils.aop.datasynchronization.DataSynchronous;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.ExcelDealFactory;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener.AnalysisBaseListener;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
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
    response.addHeader("Content-Disposition", "attachment;filename=template.xlsx");
    IOUtils.copy(resourceAsStream, outputStream);
    outputStream.flush();
  }

  @Override
  public Result uploadAndParseTemplate(MultipartFile file) throws IOException {
    AnalysisBaseListener instance = excelDealFactory.getInstance(EntityEnums.MAJOR);
    return excelDealFactory.dealMain(instance,file);
  }

  @Override
  public Result<BasePageVO<GetMajorListVO>> getMajorList(GetMajorListCommand command) {
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<Major> majorList = getBaseMapper().selectList(null);
    PageInfo pageInfo = new PageInfo(majorList);
    List<GetMajorListVO> getMajorListVOS = DozerUtils.convertList(majorList, GetMajorListVO.class);
    pageInfo.setList(getMajorListVOS);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  @DataSynchronous(type = EntityEnums.MAJOR)
  public Result<Void> updateMajorName(UpdateMajorNameCommand command) {
    Major major = getBaseMapper().selectOne(
        new LambdaQueryWrapper<Major>().eq(Major::getId, command.getId()));
    if(ObjectUtil.isNull(major)){
      throw new BusinessException(BusinessError.MAJOR_NOT_EXIST_ERROR);
    }
    if(major.getName().equals(command.getName())){
      throw new BusinessException(BusinessError.MAJOR_NAME_REPEAT_ERROR);
    }
    major.setName(command.getName());
    major.setFullName(major.getDeptName() + major.getName() + "专业");
    getBaseMapper().updateById(major);
    return ResultFactory.successResult();
  }
}
