package xyz.chnytrcy.campusepidemicsystem;

import javax.annotation.Resource;
import xyz.chnytrcy.campusepidemicsystem.mapper.AdminMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.ClassMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationPersonMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.LeaveMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.MajorMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.StreetMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Admin;
import xyz.chnytrcy.campusepidemicsystem.model.entity.ClassEntity;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Leave;
import xyz.chnytrcy.campusepidemicsystem.model.entity.LeaveDetail;
import xyz.chnytrcy.campusepidemicsystem.model.entity.LeaveHealth;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Major;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Street;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.User;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.LeaveDetailEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.LeaveEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.LeaveHealthEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.RoleEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.StudentEnums;
import xyz.chnytrcy.campusepidemicsystem.service.pc.LeaveDetailService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.LeaveHealthService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.LeaveService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.StudentService;
import xyz.chnytrcy.campusepidemicsystem.service.pc.UserService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.chnytrcy.core.utils.md5.MD5;
import xyz.chnytrcy.core.utils.random.RandomName;
import xyz.chnytrcy.core.utils.random.RandomName.NameDTO;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
public class ApplicationTest {

  /**
   * 批量生成学生数量
   */
  private static int STUDENT_NUMBER = 200;

  /**
   * 批量生成请假数量
   */
  private static int LEAVE_NUMBER = 50;

  @Value("${user.init.password}")
  private String password;

  @Value("${school.county.code}")
  private String schoolCode;

  @Autowired private ClassMapper classMapper;

  @Autowired private UserService userService;

  @Autowired private UserMapper userMapper;

  @Autowired private StudentService studentService;

  @Autowired private MajorMapper majorMapper;

  @Autowired private AdminMapper adminMapper;

  @Resource private RandomName randomName;

  @Autowired private LeaveMapper leaveMapper;

  @Autowired private LeaveService leaveService;

  @Autowired private LeaveHealthService leaveHealthService;

  @Autowired private LeaveDetailService leaveDetailService;

  @Autowired private IsolationPersonMapper isolationPersonMapper;

  @Autowired private StreetMapper streetMapper;

  /**
   * 批量增加学生
   */
  @Test
  public void test1(){
    List<ClassEntity> classList = classMapper.selectList(null);
    List<NameDTO> nameDTOS = randomName.randomNameList(STUDENT_NUMBER << 1);
    List<Student> studentList = studentService.list();
    Map<String, Major> majorMap = majorMapper.selectList(null)
        .stream()
        .collect(Collectors.toMap(Major::getCode, e -> e,(a,b) -> a));
    Set<String> codeSet = studentList.stream().map(Student::getCode).collect(Collectors.toSet());
    List<Student> key = Lists.newArrayList();
    List<String> codeList = Lists.newArrayList();
    List<User> userList = Lists.newArrayList();
    for (int i = 0; i < STUDENT_NUMBER; i++) {
      Student student = new Student();
      student.setName(nameDTOS.get(i).getName());
      student.setSex(nameDTOS.get(i).getIsMan().equals(Boolean.TRUE) ? 1 : 2);
      do{
        ClassEntity classEntity = classList.get(RandomUtil.randomInt(classList.size()));
        String codeTail;
        int codeTailNum = RandomUtil.randomInt(100);
        if(codeTailNum < 10){
          codeTail = "0" + codeTailNum;
        }else {
          codeTail = String.valueOf(codeTailNum);
        }
        student.setCode(classEntity.getCode() + codeTail);
        student.setDeptCode(classEntity.getDeptCode());
        student.setDeptName(majorMap.get(classEntity.getMajorCode()).getDeptName());
        student.setMajorCode(classEntity.getMajorCode());
        student.setMajorName(majorMap.get(classEntity.getMajorCode()).getName());
        student.setClassCode(classEntity.getCode());
        student.setClassName(classEntity.getName());
        student.setPhone("18358889334");
        student.setCreateUser("guanliyuan");
        student.setUpdateUser("guanliyuan");
        student.setReputation(100);
      }while (
          codeSet.contains(student.getCode()) || codeList.contains(student.getCode())
      );
      codeList.add(student.getCode());
      key.add(student);
    }
    codeList.forEach(e -> {
      User user = new User();
      user.setAccount(e);
      user.setPassword(MD5.SysMd5(e,password));
      user.setPhone("18358889334");
      user.setCreateUser("guanliyuan");
      user.setUpdateUser("guanliyuan");
      userList.add(user);
    });
    studentService.saveBatch(key);
    userService.saveBatch(userList);
    Set<Long> collect = userList.stream().map(User::getId).collect(Collectors.toSet());
    collect.forEach(e -> {
      userMapper.addUserRole(e, RoleEnums.STUDENT.getNumber());
    });
  }

  /**
   * 批量生成请假记录
   */
  @Test
  public void test02(){
    List<LeaveDetail> leaveDetailList = Lists.newArrayList();
    List<Leave> leaveList = Lists.newArrayList();
    List<LeaveHealth> leaveHealthList = Lists.newArrayList();
    List<Student> studentList = studentService.list();
    Map<String, Student> studentMap = studentList.stream()
        .collect(Collectors.toMap(Student::getCode, e -> e, (a, b) -> a));
    Set<String> studentCodeSet = studentList
        .stream()
        .map(Student::getCode)
        .collect(Collectors.toSet());
    /**
     *   类型(caseType)：
     *   1、还在申请状态（肯定不满足免批条件） —>  跟管理员聊天（管理员未读）
     *   2、申请状态拒绝 【有聊天情况】  没有出校记录
     *   3、申请状态成功 【有聊天情况】，但是还没出校
     *   4、免批【无聊天】，但是还没出校
     *   5、成功/免批(3,4) —> 已经出校，但还没返校
     *   6、(5)已经返校  —> 需要添加健康码颜色
     *   7、还在申请状态（肯定不满足免批条件） —>  跟管理员聊天（管理员在处理）
     *   8、成功/免批(3,4) —> 未出校
     */
    //1、随机选择学生
    Set<String> chooseStudentCode = RandomUtil.randomEleSet(studentCodeSet,LEAVE_NUMBER);
    List<String> streetSchoolList = streetMapper.selectList(
            new LambdaQueryWrapper<Street>().eq(Street::getCountyCode, schoolCode))
        .stream().map(Street::getCode).collect(Collectors.toList());
    chooseStudentCode.forEach(a -> {
      Student student = studentMap.get(a);
      Leave leave = new Leave();
      Long adminId  = adminMapper.selectOne(
          new LambdaQueryWrapper<Admin>().eq(Admin::getDeptCode, student.getDeptCode())).getUserId();
      leave.setAdminId(adminId);
      //2、随机生成预计出校以及预计返校时间
      LocalDateTime nowTime = LocalDateTime.now();
      LocalDateTime estimateEndTime;
      LocalDateTime estimateStartTime;
      do {
        estimateStartTime = randomDateTime(nowTime.plusDays(RandomUtil.randomInt(-40,20)));
        estimateEndTime = randomDateTime(nowTime.plusDays(RandomUtil.randomInt(-40,20)));
      }while (!estimateStartTime.isBefore(estimateEndTime));
      Long aLong = this.checkNewLeave(student,leaveList,estimateStartTime,estimateEndTime);
      if(aLong == -1L){
        return;
      }
      leave.setCode(student.getCode());
      leave.setName(student.getName());
      leave.setType(RandomUtil.randomInt(2));
      leave.setEstimateStartTime(estimateStartTime);
      leave.setEstimateEndTime(estimateEndTime);
      //3、随机获得目的地地点，70%是本市，30%跨市（跨市随机分布城市）
      int i = RandomUtil.randomInt(10);
      String target;
      if(i < 7){
        target = RandomUtil.randomEle(streetSchoolList);
      }else {
        List<String> streetList = streetMapper.selectList(null).stream().map(Street::getCode)
            .collect(Collectors.toList());
        target = RandomUtil.randomEle(streetList);
      }
      leave.setTarget(target);
      if (target.equals(schoolCode)) {
        leave.setIsStrideCounty(LeaveEnums.IS_STRIDE_COUNTY_NO.getCode());
      } else {
        leave.setIsStrideCounty(LeaveEnums.IS_STRIDE_COUNTY_YES.getCode());
      }
      if(estimateStartTime.getDayOfYear() == estimateEndTime.getDayOfYear()){
        leave.setIsStrideDay(LeaveEnums.IS_STRIDE_DAY_NO.getCode());
      }else {
        leave.setIsStrideDay(LeaveEnums.IS_STRIDE_DAY_YES.getCode());
      }
      //4、判断是否免批
      if(leave.getIsStrideDay().equals(LeaveEnums.IS_STRIDE_DAY_NO.getCode()) &&
      leave.getIsStrideCounty().equals(LeaveEnums.IS_STRIDE_COUNTY_NO.getCode()) &&
      student.getReputation() >= StudentEnums.REPUTATION_GOOD.getCode()){
        //4.1、免批情况
        leave.setApprovalResult(LeaveEnums.APPROVAL_RESULT_AGREE.getCode());
        leave.setIsEnd(LeaveEnums.IS_END_YES.getCode());
        if(leave.getEstimateEndTime().isBefore(LocalDateTime.now())){
          leave.setIsOverdue(LeaveEnums.IS_OVERDUE_YES.getCode());
        }else {
          leave.setIsOverdue(LeaveEnums.IS_OVERDUE_NO.getCode());
        }
        leave.setReason("测试请假免批");
        leave.setCaseType(4);
      }else {
        //4.2、不是免批的情况
        //4.2.1、还在申请状态，跟管理员pk中
        int i1 = RandomUtil.randomInt(2);
        leave.setIsEnd(LeaveEnums.IS_END_NO.getCode());
        if(leave.getEstimateEndTime().isBefore(LocalDateTime.now())){
          leave.setIsOverdue(LeaveEnums.IS_OVERDUE_YES.getCode());
        }else {
          leave.setIsOverdue(LeaveEnums.IS_OVERDUE_NO.getCode());
        }
        if(i1 == 1){
          //管理员未处理状态，此时只需要添加一条由学生提出的详情
          leave.setApprovalResult(LeaveEnums.APPROVAL_RESULT_NOT_PROCESSED.getCode());
          leave.setReason("测试请假跟管理员pk中 管理员未读");
          leave.setCaseType(1);
        }else {
          int i2 = RandomUtil.randomInt(3);
          if(i2 == 0){
            //4.2.2、管理员已读还在处理pk中
            leave.setApprovalResult(LeaveEnums.APPROVAL_RESULT_DEAL.getCode());
            leave.setReason("测试请假跟管理员pk中 管理员已读处理中");
            leave.setIsEnd(LeaveEnums.IS_END_NO.getCode());
            leave.setCaseType(7);
          }else if(i2 == 1){
            //4.2.3、管理员结束pk，拒绝
            leave.setApprovalResult(LeaveEnums.APPROVAL_RESULT_REJECT.getCode());
            leave.setIsEnd(LeaveEnums.IS_END_YES.getCode());
            leave.setReason("测试请假跟管理员pk结束 拒绝");
            leave.setCaseType(2);
          }else if(i2 == 2){
            //4.2.4、管理员结束pk，同意
            leave.setApprovalResult(LeaveEnums.APPROVAL_RESULT_AGREE.getCode());
            leave.setIsEnd(LeaveEnums.IS_END_YES.getCode());
            leave.setReason("测试请假跟管理员pk结束 同意");
            leave.setCaseType(3);
          }
        }
      }
      //5、是否已出校
      //5.1、具有出校资格
      if(leave.getCaseType() == 3 || leave.getCaseType() == 4){
        int startP = RandomUtil.randomInt(3);
        if(startP == 0){
          //5.1.1、还没出校
          leave.setIsStart(LeaveEnums.IS_START_NO.getCode());
          leave.setIsReturn(LeaveEnums.IS_RETURN_NO.getCode());
          leave.setCaseType(8);
        }else if(startP == 1){
          //5.1.2、出校，但未返校
          leave.setIsStart(LeaveEnums.IS_START_YES.getCode());
          leave.setIsReturn(LeaveEnums.IS_RETURN_NO.getCode());
          leave.setCaseType(5);
        }else {
          //5.1.3、出校，已返校
          leave.setIsStart(LeaveEnums.IS_START_YES.getCode());
          leave.setIsReturn(LeaveEnums.IS_RETURN_YES.getCode());
          //todo 填充真实返校时间
          LocalDateTime actualStartTime;
          LocalDateTime actualEndTime;
          do {
            actualStartTime = randomDateTime(nowTime.plusDays(RandomUtil.randomInt(-40,20)));
            actualEndTime = randomDateTime(nowTime.plusDays(RandomUtil.randomInt(-40,20)));
          }while (!actualStartTime.isBefore(actualEndTime) ||
              actualStartTime.isBefore(estimateStartTime) ||
              actualEndTime.isAfter(estimateEndTime));
          leave.setActualStartTime(actualStartTime);
          leave.setActualEndTime(actualEndTime);
          leave.setCaseType(6);
        }
      }else {
        leave.setIsStart(LeaveEnums.IS_START_NO.getCode());
        leave.setIsReturn(LeaveEnums.IS_RETURN_NO.getCode());
      }
      leave.setCreateUser("supAdmin");
      leave.setUpdateUser("supAdmin");
      leaveList.add(leave);
    });
    leaveService.saveBatch(leaveList);
    //6、是否返校 生成健康码颜色
    List<Long> returnCodeList = leaveList.stream()
        .filter(e -> e.getIsReturn().equals(LeaveEnums.IS_RETURN_YES.getCode()))
        .map(Leave::getId)
        .collect(Collectors.toList());
    if(CollUtil.isNotEmpty(returnCodeList)){
      returnCodeList.forEach(a -> {
        LeaveHealth leaveHealth = new LeaveHealth(a, "leave_7f8ff067c7dc2d63d1ec71f8746a521c",
            LeaveHealthEnums.NUCLEIC_ACID_FEMININE.getCode());
        leaveHealth.setCreateUser("supAdmin");
        leaveHealth.setUpdateUser("supAdmin");
        leaveHealthList.add(leaveHealth);
      });
    }
    if(CollUtil.isNotEmpty(leaveHealthList)){
      leaveHealthService.saveBatch(leaveHealthList);
    }
    //7、统一生成请假详情
    List<Leave> collect = leaveList.stream().filter(e -> e.getCaseType() != 4)
        .collect(Collectors.toList());
    collect.forEach(e -> {
      LeaveDetail leaveDetail = new LeaveDetail(
          e.getId(),
          LeaveDetailEnums.TYPE_STUDENT.getCode(),
          LeaveDetailEnums.TYPE_STUDENT.getDesc());
      leaveDetail.setCreateUser("supAdmin");
      leaveDetail.setUpdateUser("supAdmin");
      leaveDetailList.add(leaveDetail);
      if(e.getCaseType() != 1){
        int t = RandomUtil.randomInt(7);
        for (int i = 0; i <= t; i++) {
          if(i % 2 == 0){
            LeaveDetail leaveDetail1 = new LeaveDetail(
                e.getId(),
                LeaveDetailEnums.TYPE_ADMIN_REPEAT.getCode(),
                LeaveDetailEnums.TYPE_ADMIN_REPEAT.getDesc());
            leaveDetail1.setUpdateUser("supAdmin");
            leaveDetail1.setCreateUser("supAdmin");
            leaveDetailList.add(leaveDetail1);
          }else {
            LeaveDetail leaveDetail1 = new LeaveDetail(
                e.getId(),
                LeaveDetailEnums.TYPE_STUDENT_AGAIN.getCode(),
                LeaveDetailEnums.TYPE_STUDENT_AGAIN.getDesc());
            leaveDetail1.setCreateUser("supAdmin");
            leaveDetail1.setUpdateUser("supAdmin");
            leaveDetailList.add(leaveDetail1);
          }
        }
        if(e.getCaseType() != 7){
          LeaveDetail leaveDetail1 = new LeaveDetail(
              e.getId(),
              LeaveDetailEnums.TYPE_END.getCode(),
              LeaveDetailEnums.TYPE_END.getDesc());
          leaveDetail1.setCreateUser("supAdmin");
          leaveDetail1.setUpdateUser("supAdmin");
          leaveDetailList.add(leaveDetail1);
        }
      }
    });
    if(CollUtil.isNotEmpty(leaveDetailList)){
      leaveDetailService.saveBatch(leaveDetailList);
    }
  }

  //todo 保证请假不重复单单去表里查找是没有用的，还需要实时遍历leaveList中是否由重复的记录
  private Long checkNewLeave(Student student,List<Leave> leaveList,LocalDateTime start,LocalDateTime end){
    //判断列表中是否由时间交叉
    List<Leave> collect = leaveList.stream().filter(e -> e.getCode().equals(student.getCode()))
        .collect(Collectors.toList());
    for (Leave e : collect) {
      if((end.isAfter(e.getEstimateStartTime()) && end.isBefore(e.getEstimateEndTime())) ||
          (start.isBefore(e.getEstimateStartTime()) && end.isAfter(e.getEstimateEndTime())) ||
          (start.isAfter(e.getEstimateStartTime()) && start.isBefore(e.getEstimateEndTime()))){
        return -1L;
      }
    }
    //检查是否有未完成的请假
    Leave unfinishedLeave = leaveMapper.queryUnfinishedLeave(student.getCode());
    boolean overdue = false;
    if(!Objects.isNull(unfinishedLeave)){
      if(LeaveEnums.APPROVAL_RESULT_NOT_PROCESSED.getCode().equals(unfinishedLeave.getApprovalResult()) ||
          LeaveEnums.APPROVAL_RESULT_DEAL.getCode().equals(unfinishedLeave.getApprovalResult())){
        return -1L;
      }
      if(!(LeaveEnums.IS_START_NO.getNumber().equals(unfinishedLeave.getIsStart()) &&
          unfinishedLeave.getEstimateEndTime().isBefore(LocalDateTime.now()))){
        if(LeaveEnums.IS_RETURN_NO.getNumber().equals(unfinishedLeave.getIsReturn())){
          return -1L;
        }
      }else {
        overdue = true;
      }
    }
    //判断该学生的合法性（不能为隔离人员）
    IsolationPerson isolationPerson = isolationPersonMapper.selectOne(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getCode, student.getCode())
            .ne(IsolationPerson::getState, IsolationPersonEnums.STATE_END.getCode()));
    if(!Objects.isNull(isolationPerson)){
      return -1L;
    }
    if(ObjectUtil.isNotNull(unfinishedLeave)){
      if(overdue){
        return unfinishedLeave.getId();
      }else {
        return 0L;
      }
    }else {
      return 0L;
    }
  }

  private LocalDateTime randomDateTime(LocalDateTime dateTime){
    return dateTime
        .withHour(0)
        .withMinute(0)
        .withSecond(0)
        .withNano(0)
        .plusHours(RandomUtil.randomInt(24))
        .plusMinutes(RandomUtil.randomInt(60))
        .plusSeconds(RandomUtil.randomInt(60));
  }
}