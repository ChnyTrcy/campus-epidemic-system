package xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener;

import xyz.chnytrcy.campusepidemicsystem.mapper.DeptMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.MajorMapper;
import xyz.chnytrcy.campusepidemicsystem.model.constance.StudentConstance;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Dept;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Major;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.bo.MajorBO;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener
 * @ClassName: MajorListener
 * @Author: ChnyTrcy
 * @Description: 专业模版Excel解析监听器
 * @Date: 2022/12/15 17:03
 * @Version: 1.0
 */
@Slf4j
@Component
public class MajorListener extends AnalysisBaseListener<MajorBO, Major> {

  static {
    log.info("注入专业Excel解析监听器成功......");
  }

  @Autowired private MajorMapper majorMapper;

  @Autowired private DeptMapper deptMapper;

  @Override
  protected void fillData(List<Major> data) {
    Map<String, String> deptMap = deptMapper.selectList(null).stream()
        .collect(Collectors.toMap(Dept::getCode, Dept::getName));
    data.forEach(e -> {
      e.setDeptName(deptMap.get(e.getDeptCode()));
      e.setFullName(e.getDeptName() + e.getName() + "专业");
    });
  }

  @Override
  protected void batchInsert() {
    if(CollUtil.isEmpty(addList)){
      log.warn("没有符合条件的数据，无需插入");
    }else {
      log.debug("开始批量插入");
      addList.forEach(e -> {
        majorMapper.insert(e);
      });
    }
  }

  @Override
  protected void cleanList() {
    addList.clear();
    boList.clear();
    addCodeList.clear();
  }

  @Override
  protected Boolean validationData(MajorBO bo) {
    String deptCode = bo.getDeptCode();
    List<String> deptList = deptMapper.selectList(null).stream().map(Dept::getCode)
        .collect(Collectors.toList());
    if(deptCode.length() != StudentConstance.DEPT_CODE_LENGTH){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("院系编号长度错误")
          .build());
      return false;
    }
    if(!deptList.contains(deptCode)){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("不存在此院系编号")
          .build());
      return false;
    }
    String code = bo.getCode();
    if(code.length() != StudentConstance.MAJOR_CODE_LENGTH){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("专业编号长度错误")
          .build());
      return false;
    }
    if(!code.substring(0,StudentConstance.DEPT_CODE_LENGTH).equals(deptCode)){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("专业编号与院系编号不匹配")
          .build());
      return false;
    }
    List<String> codeList = majorMapper.selectList(null).stream().map(Major::getCode)
        .collect(Collectors.toList());
    if(codeList.contains(code) || addCodeList.contains(code)){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("专业编号重复")
          .build());
      return false;
    }
    addCodeList.add(code);
    return true;
  }

  @Override
  public Class<MajorBO> getT() {
    return MajorBO.class;
  }

  @Override
  public void invoke(MajorBO majorBO, AnalysisContext analysisContext) {
    log.debug("解析到一条数据：{}", JSON.toJSONString(majorBO));
    if(validationData(majorBO)){
      boList.add(majorBO);
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.debug("解析完成...");
    addList = DozerUtils.convertList(boList, Major.class);
    this.fillData(addList);
    this.batchInsert();
    this.cleanList();
  }
}
