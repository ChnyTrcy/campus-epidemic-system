package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener;

import chnytrcy.xyz.campusepidemicsystem.mapper.ClassMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.DeptMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.MajorMapper;
import chnytrcy.xyz.campusepidemicsystem.model.constance.StudentConstance;
import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo.ClassBO;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener
 * @ClassName: ClassListener
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/16 14:10
 * @Version: 1.0
 */
@Slf4j
@Component
public class ClassListener extends AnalysisBaseListener<ClassBO, ClassEntity>{

  @Autowired private DeptMapper deptMapper;

  @Autowired private MajorMapper majorMapper;

  @Autowired private ClassMapper classMapper;

  @Override
  protected void fillData(List<ClassEntity> data) {
    data.forEach(e -> {
      e.setYear("20" + e.getCode().substring(0,2));
      e.setYearHalf(e.getCode().substring(0,2));
    });
  }

  @Override
  protected void batchInsert() {
    addList.forEach(e -> {
      classMapper.insert(e);
    });
  }

  @Override
  protected void cleanList() {
    addCodeList.clear();
    boList.clear();
    addCodeList.clear();
  }

  @Override
  protected Boolean validationData(ClassBO bo) {
    String deptCode = bo.getDeptCode();
    if(deptCode.length() != StudentConstance.DEPT_CODE_LENGTH){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("院系编号长度错误")
          .build());
      return false;
    }
    List<String> deptList = deptMapper.selectList(null).stream().map(Dept::getCode)
        .collect(Collectors.toList());
    if(!deptList.contains(deptCode)){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("院系不存在")
          .build());
      return false;
    }
    String majorCode = bo.getMajorCode();
    List<String> majorList = majorMapper.selectList(null).stream().map(Major::getCode)
        .collect(Collectors.toList());
    if(majorCode.length() != StudentConstance.MAJOR_CODE_LENGTH){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("专业编号长度错误")
          .build());
      return false;
    }
    if(!majorList.contains(majorCode)){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("专业不存在")
          .build());
      return false;
    }
    String classCode = bo.getCode();
    List<String> classCodeList = classMapper.selectList(null).stream().map(ClassEntity::getCode)
        .collect(Collectors.toList());
    if(classCode.length() != StudentConstance.CLASS_CODE_LENGTH){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("班级编号长度错误")
          .build());
      return false;
    }
    if(classCodeList.contains(classCode) || addCodeList.contains(classCode)){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("班级编号重复")
          .build());
      return false;
    }
    if(!majorCode.substring(0,StudentConstance.DEPT_CODE_LENGTH).equals(deptCode) ||
    !classCode.substring(StudentConstance.DEPT_CODE_LENGTH + 2,classCode.length() - 2).equals(majorCode)){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("院系编号、专业编号、班级编号匹配失败")
          .build());
      return false;
    }
    addCodeList.add(classCode);
    return true;
  }

  @Override
  public Class<ClassBO> getT() {
    return ClassBO.class;
  }

  @Override
  public void invoke(ClassBO classBO, AnalysisContext analysisContext) {
    log.debug("解析道一条数据：{}", JSON.toJSONString(classBO));
    if(validationData(classBO)){
      boList.add(classBO);
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.debug("解析完成...");
    addList = DozerUtils.convertList(boList, ClassEntity.class);
    this.fillData(addList);
    this.batchInsert();
    this.cleanList();
  }
}
