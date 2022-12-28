package xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener;

import xyz.chnytrcy.campusepidemicsystem.common.ClassCommon;
import xyz.chnytrcy.campusepidemicsystem.common.DeptCommon;
import xyz.chnytrcy.campusepidemicsystem.common.MajorCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.ClassMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.constance.StudentConstance;
import xyz.chnytrcy.campusepidemicsystem.model.entity.ClassEntity;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Dept;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Major;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.User;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.RoleEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.SexEnums;
import xyz.chnytrcy.campusepidemicsystem.service.pc.UserService;
import xyz.chnytrcy.campusepidemicsystem.utils.dozer.DozerUtils;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.bo.StudentBO;
import xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.fastjson.JSON;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import xyz.chnytrcy.core.utils.md5.MD5;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.listener
 * @ClassName: StudentListener
 * @Author: ChnyTrcy
 * @Description: 学生模板Excel解析监听器
 * @Date: 2022/11/28 20:46
 * @Version: 1.0
 */
@Slf4j
@Component
public class StudentListener extends AnalysisBaseListener<StudentBO,Student> {

  @Value("${user.init.password}")
  private String userInitPassword;

  @Autowired private ClassMapper classMapper;

  @Autowired private DeptCommon deptCommon;

  @Autowired private MajorCommon majorCommon;

  @Autowired private ClassCommon classCommon;

  @Autowired private StudentMapper studentMapper;

  @Resource private UserService userService;

  @Autowired private UserMapper userMapper;

  private List<User> userList = Lists.newArrayList();

  private List<Long> userIdList = Lists.newArrayList();

  static {
    log.info("注入学生Excel解析监听器成功......");
  }

  @Override
  public void invoke(StudentBO studentBO, AnalysisContext analysisContext) {
    log.debug("解析到一条数据:{}", JSON.toJSONString(studentBO));
    Boolean validation = validationData(studentBO);
    if(validation){
      boList.add(studentBO);
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.debug("解析完成...");
    addList = DozerUtils.convertList(boList,Student.class);
    this.fillData(addList);
    this.batchInsert();
    this.cleanList();
  }

  @Override
  protected void fillData(List<Student> data){
    log.debug("开始填充数据");
    data.forEach(e -> {
      e.setDeptName(deptCommon.deptHashMap().get(e.getDeptCode()));
      e.setMajorName(majorCommon.majorHashMapToName().get(e.getMajorCode()));
      e.setClassName(classCommon.classHashMapToName().get(e.getClassCode()));
    });

  }

  @Override
  protected void batchInsert(){
    if(CollUtil.isEmpty(addList)){
      log.warn("没有符合条件的数据，无需插入");
    }else {
      log.debug("开始批量插入");
      //todo 因为依赖循环的关系，这里只能使用Mapper而不能用IService的批量操作
      addList.forEach(e -> {
        studentMapper.insert(e);
      });
      addList.forEach(e -> {
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
      userMapper.batchAddUserRole(userIdList, RoleEnums.STUDENT.getNumber());
    }
  }

  @Override
  protected void cleanList(){
    boList.clear();
    addCodeList.clear();
    addList.clear();
    this.userList.clear();
    this.userIdList.clear();
  }

  @Override
  protected Boolean validationData(StudentBO bo){
    String deptCode = bo.getDeptCode();
    if(deptCode.length() != StudentConstance.DEPT_CODE_LENGTH){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("院系编号长度错误").build());
      return false;
    }
    List<Dept> deptList = deptCommon.deptList();
    List<String> deptCodeList = deptList.stream().map(Dept::getCode).collect(Collectors.toList());
    if(!deptCodeList.contains(deptCode)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("不存在此院系编号")
          .build());
      return false;
    }
    String majorCode = bo.getMajorCode();
    if(majorCode.length() != StudentConstance.MAJOR_CODE_LENGTH){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("专业编号长度错误")
          .build());
      return false;
    }
    List<Major> majorList = majorCommon.majorList();
    List<String> majorCodeList = majorList.stream().map(Major::getCode).collect(Collectors.toList());
    if(!majorCodeList.contains(majorCode)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("不存在此专业编号")
          .build());
      return false;
    }
    String classCode = bo.getClassCode();
    if(classCode.length() != StudentConstance.CLASS_CODE_LENGTH) {
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("班级编号长度错误")
          .build());
      return false;
    }
    List<ClassEntity> classList = classMapper.selectList(null);
    List<String> classCodeList = classList.stream().map(ClassEntity::getCode)
        .collect(Collectors.toList());
    if(!classCodeList.contains(classCode)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("不存在班级编号")
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
    if(ObjectUtil.isNotNull(bo.getEmergencyPhone())){
      String emergencyPhone = bo.getEmergencyPhone();
      if(!Validator.isMobile(emergencyPhone)){
        errorList.add(ErrorEntity.builder()
            .row(analysisRow++)
            .content(JSON.toJSONString(bo))
            .errorContent("无效的紧急联系方式")
            .build());
        return false;
      }
    }
    if(ObjectUtil.isNotNull(bo.getIdCard())){
      String idCard = bo.getIdCard();
      if(!IdcardUtil.isValidCard(idCard)){
        errorList.add(ErrorEntity.builder()
            .row(analysisRow++)
            .content(JSON.toJSONString(bo))
            .errorContent("无效的身份证号")
            .build());
        return false;
      }
    }
    String code = bo.getCode();
    if(!code.substring(0,8).equals(classCode) || !classCode.substring(2,6).equals(majorCode) ||
    !majorCode.substring(0,2).equals(deptCode)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("院系编号、专业编号、班级编号、学号之间相关联错误")
          .build());
      return false;
    }
    List<String> studentCodeList = studentMapper.selectList(null).stream().map(Student::getCode)
        .collect(Collectors.toList());
    if(studentCodeList.contains(code) || addCodeList.contains(code)){
      errorList.add(ErrorEntity.builder()
          .row(analysisRow++)
          .content(JSON.toJSONString(bo))
          .errorContent("学号存在重复")
          .build());
      return false;
    }
    addCodeList.add(code);
    return true;
  }

  @Override
  public Class<StudentBO> getT() {
    return StudentBO.class;
  }


}
