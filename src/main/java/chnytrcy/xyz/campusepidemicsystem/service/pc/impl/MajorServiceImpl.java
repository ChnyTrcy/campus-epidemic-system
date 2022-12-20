package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.DataSynchronous;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.MajorMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.major.GetMajorListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.major.UpdateMajorNameCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.GetMajorListVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.major.MajorListVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.MajorService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ExcelDealFactory;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener.AnalysisBaseListener;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
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
