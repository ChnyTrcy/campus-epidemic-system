package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener;

import chnytrcy.xyz.campusepidemicsystem.common.DeptCommon;
import chnytrcy.xyz.campusepidemicsystem.common.MajorCommon;
import chnytrcy.xyz.campusepidemicsystem.mapper.TeacherMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.UserMapper;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.RoleEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.SexEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.TeacherEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.UserService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo.TeacherBO;
import chnytrcy.xyz.campusepidemicsystem.utils.md5.MD5;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener
 * @ClassName: TeacherListener
 * @Author: ChnyTrcy
 * @Description: 教职工Excel解析监听器
 * @Date: 2022/11/30 14:20
 * @Version: 1.0
 */
@Slf4j
@Component
public class TeacherListener extends AnalysisEventListener<TeacherBO> {

  @Value("${user.init.password}")
  private String userInitPassword;

  @Autowired private DeptCommon deptCommon;

  @Autowired private TeacherMapper teacherMapper;

  @Resource private UserService userService;

  @Autowired private UserMapper userMapper;

  private Integer analysisRow = 3;

  private List<ErrorEntity> errorList = Lists.newArrayList();

  private List<TeacherBO> teacherBOList = Lists.newArrayList();

  private List<Teacher> teacherList = Lists.newArrayList();

  private List<String> addCodeList = Lists.newArrayList();

  private List<User> userList = Lists.newArrayList();

  private List<Long> userIdList = Lists.newArrayList();

  static {
    log.info("注入教职工Excel解析监听器成功......");
  }

  @Override
  public void invoke(TeacherBO teacherBO, AnalysisContext analysisContext) {
    log.debug("解析道一条数据：{}",JSON.toJSONString(teacherBO));
    Boolean validationData = validationData(teacherBO);
    if(validationData){
      teacherBOList.add(teacherBO);
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.debug("解析完成...");
    teacherList = DozerUtils.convertList(teacherBOList, Teacher.class);
    this.fillDataName();
    this.batchInsert();
    this.cleanList();
  }

  public List<ErrorEntity> getErrorList(){
    List<ErrorEntity> errorEntities = ObjectUtil.cloneByStream(this.errorList);
    errorList.clear();
    return errorEntities;
  }

  private void cleanList(){
    this.teacherBOList.clear();
    this.addCodeList.clear();
    this.teacherList.clear();
    this.userList.clear();
    this.userIdList.clear();
  }

  /**
   * 批量插入数据
   */
  private void batchInsert(){
    if(CollUtil.isEmpty(teacherList)){
      log.warn("没有符合条件的数据，无需插入");
    }else {
      log.debug("开始批量插入");
      teacherList.forEach(e -> {
        teacherMapper.insert(e);
      });
      teacherList.forEach(e -> {
        User user = new User(
            e.getCode(),
            MD5.SysMd5(e.getCode(),userInitPassword),
            e.getPhone()
        );
        user.setId(e.getId());
        userList.add(user);
        userIdList.add(e.getId());
      });
      userService.saveBatch(userList);
      userMapper.batchAddUserRole(userIdList, RoleEnums.TEACHER.getNumber());
    }
  }

  /**
   * 填充数据
   */
  private void fillDataName(){
    log.debug("开始填充数据...");
    teacherList.forEach(e -> {
      e.setDeptName(deptCommon.deptHashMap().get(e.getDeptCode()));
      e.setEpidemicMark(TeacherEnums.EPIDEMIC_MARK_NO.getNumber());
    });
  }

  /**
   * 验证数据有效性
   */
  private Boolean validationData(TeacherBO bo){
    log.debug("验证数据有效性");
    String deptCode = bo.getDeptCode();
    List<Dept> deptList = deptCommon.deptList();
    List<String> deptCodeList = deptList.stream().map(Dept::getCode).collect(Collectors.toList());
    if(!deptCodeList.contains(deptCode)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("院系编号不存在")
          .build());
      return false;
    }
    String phone = bo.getPhone();
    if(!Validator.isMobile(phone)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("无效的联系方式")
          .build());
      return false;
    }
    String idCard = bo.getIdCard();
    if(!IdcardUtil.isValidCard(idCard)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("无效的身份证号")
          .build());
      return false;
    }
    Integer sex = bo.getSex();
    HashSet<Integer> sexSets = CollUtil.newHashSet(SexEnums.MAN.getCode(),
        SexEnums.WOMAN.getCode());
    if(!sexSets.contains(sex)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("性别错误")
          .build());
      return false;
    }
    String code = bo.getCode();
    List<String> teacherCodeList = teacherMapper.selectList(null).stream().map(Teacher::getCode)
        .collect(Collectors.toList());
    if(teacherCodeList.contains(code) || addCodeList.contains(code)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("工号不能重复")
          .build());
      return false;
    }
    addCodeList.add(code);
    return true;
  }
}
