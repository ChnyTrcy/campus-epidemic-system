package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener;

import chnytrcy.xyz.campusepidemicsystem.common.DeptCommon;
import chnytrcy.xyz.campusepidemicsystem.mapper.DeptMapper;
import chnytrcy.xyz.campusepidemicsystem.model.constance.StudentConstance;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.AnalysisBaseListener;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo.DeptBO;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener
 * @ClassName: DeptListener
 * @Author: ChnyTrcy
 * @Description: 院系模版Excel解析监听器
 * @Date: 2022/12/15 14:20
 * @Version: 1.0
 */
@Slf4j
@Component
public class DeptListener extends AnalysisBaseListener<DeptBO,Dept> {

  @Autowired private DeptCommon deptCommon;

  @Autowired private DeptMapper deptMapper;

  private List<String> addCodeList = Lists.newArrayList();

  private List<DeptBO> deptBOList = Lists.newArrayList();

  private List<Dept> deptList = Lists.newArrayList();

  static {
    log.info("注入院系Excel解析监听器成功......");
  }

  @Override
  public void invoke(DeptBO deptBO, AnalysisContext analysisContext) {
    log.debug("解析道一条数据：{}", JSON.toJSONString(deptBO));
    Boolean aBoolean = validationData(deptBO);
    if(aBoolean){
      deptBOList.add(deptBO);
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.debug("解析完成...");
    deptList = DozerUtils.convertList(deptBOList, Dept.class);
    this.batchInsert();
    this.cleanList();
  }

  protected void cleanList(){
    this.addCodeList.clear();
    this.deptBOList.clear();
    this.deptList.clear();
  }

  @Override
  protected void fillData(List<Dept> data) {}

  protected void batchInsert(){
    if(CollUtil.isEmpty(deptList)){
      log.warn("没有符合条件的数据，无需插入");
    }else {
      log.debug("开始批量插入");
      deptList.forEach(e -> {
        deptMapper.insert(e);
      });
      log.debug("批量插入结束");
    }
  }

  protected Boolean validationData(DeptBO bo){
    String code = bo.getCode();
    if(code.length() != StudentConstance.DEPT_CODE_LENGTH){
      errorList.add(ErrorEntity.builder()
              .row(analysisRow++)
              .content(JSON.toJSONString(bo))
              .errorContent("院系编号长度错误")
          .build());
      return false;
    }
    List<String> deptNameList = deptCommon.deptList().stream().map(Dept::getCode)
        .collect(Collectors.toList());
    if(deptNameList.contains(code) || addCodeList.contains(code)){
      return false;
    }
    addCodeList.add(code);
    return true;
  }
}
