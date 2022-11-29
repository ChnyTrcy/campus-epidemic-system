package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener;

import chnytrcy.xyz.campusepidemicsystem.common.ClassCommon;
import chnytrcy.xyz.campusepidemicsystem.common.DeptCommon;
import chnytrcy.xyz.campusepidemicsystem.common.MajorCommon;
import chnytrcy.xyz.campusepidemicsystem.common.StudentCommon;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import chnytrcy.xyz.campusepidemicsystem.mapper.ClassMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.UserMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.legitimate.LegitimateStudent;
import chnytrcy.xyz.campusepidemicsystem.model.constance.StudentConstance;
import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.RoleEnums;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StudentService;
import chnytrcy.xyz.campusepidemicsystem.service.pc.UserService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo.StudentBO;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import chnytrcy.xyz.campusepidemicsystem.utils.md5.MD5;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener
 * @ClassName: StudentListener
 * @Author: ChnyTrcy
 * @Description: 学生模板Excel解析监听器
 * @Date: 2022/11/28 20:46
 * @Version: 1.0
 */
@Slf4j
@Component
public class StudentListener extends AnalysisEventListener<StudentBO> {

  @Value("${user.init.password}")
  private String userInitPassword;

  @Autowired private ClassMapper classMapper;

  @Autowired private DeptCommon deptCommon;

  @Autowired private MajorCommon majorCommon;

  @Autowired private ClassCommon classCommon;

  @Autowired private StudentMapper studentMapper;

  @Resource private UserService userService;

  @Autowired private UserMapper userMapper;

  private List<ErrorEntity> errorList = Lists.newArrayList();

  private List<StudentBO> studentBOList = Lists.newArrayList();

  private List<String> addCodeList = Lists.newArrayList();

  private List<Student> studentList = Lists.newArrayList();

  private List<User> userList = Lists.newArrayList();

  private List<Long> userIdList = Lists.newArrayList();

  private Integer analysisRow = 2;

  static {
    log.info("注入Excel解析监听器成功......");
  }

  @Override
  public void invoke(StudentBO studentBO, AnalysisContext analysisContext) {
    log.info("解析到一条数据:{}", JSON.toJSONString(studentBO));
    Boolean validation = validationData(studentBO);
    if(validation){
      studentBOList.add(studentBO);
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.info("解析完成...");
    studentList = DozerUtils.convertList(studentBOList,Student.class);
    this.fillDataName(studentList);
    this.batchInsert();
    this.cleanList();
  }

  private void fillDataName(List<Student> data){
    log.info("开始填充数据");
    data.forEach(e -> {
      e.setDeptName(deptCommon.deptHashMap().get(e.getDeptCode()));
      e.setMajorName(majorCommon.majorHashMapToName().get(e.getMajorCode()));
      e.setClassName(classCommon.classHashMapToName().get(e.getClassCode()));
    });

  }

  private void batchInsert(){
    log.info("开始批量插入");
    //todo 因为依赖循环的关系，这里只能使用Mapper而不能用IService的批量
    studentList.forEach(e -> {
      studentMapper.insert(e);
    });
    studentList.forEach(e -> {
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

  protected void cleanList(){
    this.errorList.clear();
    this.studentBOList.clear();
    this.addCodeList.clear();
    this.studentList.clear();
    this.userList.clear();
    this.userIdList.clear();
  }

  private Boolean validationData(StudentBO bo){
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
          .errorContent("不存在此院系编号")
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
          .errorContent("无效的紧急联系方式")
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



}
