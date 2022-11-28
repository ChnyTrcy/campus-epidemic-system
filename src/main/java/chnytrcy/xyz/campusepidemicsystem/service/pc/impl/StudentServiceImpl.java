package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.common.ClassCommon;
import chnytrcy.xyz.campusepidemicsystem.common.DeptCommon;
import chnytrcy.xyz.campusepidemicsystem.common.MajorCommon;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.ClassMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.DeptMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.FeedbackAcceptanceMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.HolidayStreetMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationPersonMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.MajorMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.UserMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.AddStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.DeleteStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.QueryStudentByKeywordCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.UpdateStudentCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.legitimate.LegitimateStudent;
import chnytrcy.xyz.campusepidemicsystem.model.entity.ClassEntity;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.entity.FeedbackAcceptance;
import chnytrcy.xyz.campusepidemicsystem.model.entity.HolidayStreet;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationDetail;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Major;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.FeedbackAcceptanceEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.RoleEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.student.QueryStudentByKeywordVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.StudentService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo.StudentBO;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener.StudentListener;
import chnytrcy.xyz.campusepidemicsystem.utils.md5.MD5;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: StudentServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 3:14 PM
 * @Version: 1.0
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

  @Value("${user.init.password}")
  private String userInitPassword;

  @Autowired private DeptMapper deptMapper;

  @Autowired private MajorMapper majorMapper;

  @Autowired private ClassMapper classMapper;

  @Autowired private UserMapper userMapper;

  @Autowired private FeedbackAcceptanceMapper feedbackAcceptanceMapper;

  @Autowired private IsolationPersonMapper isolationPersonMapper;

  @Autowired private LeaveMapper leaveMapper;

  @Autowired private HolidayStreetMapper holidayStreetMapper;

  @Autowired private IsolationDetailMapper isolationDetailMapper;

  @Autowired private DeptCommon deptCommon;

  @Autowired private MajorCommon majorCommon;

  @Autowired private ClassCommon classCommon;

  @Autowired private StudentListener studentListener;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> addStudent(AddStudentCommand command) {
    this.checkStudentInformationMatch(command,true);
    Student convert = DozerUtils.convert(command, Student.class);
    convert.setDeptName(deptCommon.deptHashMap().get(convert.getDeptCode()));
    convert.setMajorName(majorCommon.majorHashMapToName().get(convert.getMajorCode()));
    convert.setClassName(classCommon.classHashMapToName().get(convert.getClassCode()));
    getBaseMapper().insert(convert);
    addStudentUser(convert);
    return ResultFactory.successResult();
  }

  @Override
  public Result<BasePageVO<QueryStudentByKeywordVO>>  queryStudentByKeyword(
      QueryStudentByKeywordCommand command) {
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<Student> studentList = getBaseMapper().queryStudentByKeyword(command.getKeyword(),
        command.getWordType());
    PageInfo pageInfo = new PageInfo(studentList);
    List<QueryStudentByKeywordVO> key = DozerUtils.convertList(studentList,
        QueryStudentByKeywordVO.class);
    pageInfo.setList(key);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> updateStudent(UpdateStudentCommand command) {
    this.checkStudentInformationMatch(command,true);
    Student convert = DozerUtils.convert(command, Student.class);
    //修改学生表
    getBaseMapper().updateById(convert);
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> deleteStudent(DeleteStudentCommand command) {
    this.deleteStudentCheck(command.getCode());
    this.deleteStudentOperate(command.getCode());
    return ResultFactory.successResult();
  }

  @Override
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
    InputStream resourceAsStream = this.getClass().getClassLoader()
        .getResourceAsStream("excelTemplates/studentTemplate.xlsx");
//    PrintFileContent.printFileContent(resourceAsStream);
    OutputStream outputStream = response.getOutputStream();
    response.setContentType("application/x-download");
    response.addHeader("Content-Disposition", "attachment;filename=studentTemplate.xlsx");
    IOUtils.copy(resourceAsStream, outputStream);
    outputStream.flush();
  }

  @Override
  public Result<Void> uploadAndParseTemplate(MultipartFile file) throws IOException {
    InputStream inputStream = file.getInputStream();
    EasyExcelFactory
        .read(inputStream, StudentBO.class,studentListener)
        .sheet(0)
        .headRowNumber(2)
        .doReadSync();
    return null;
  }

  /**
   * 删除学生前的相关表检查
   * @param code 学号
   */
  private void deleteStudentCheck(String code){
    //检查是否有未完成的反馈受理表
    Long feedbackAcceptanceCount = feedbackAcceptanceMapper.selectCount(new LambdaQueryWrapper<FeedbackAcceptance>()
        .eq(FeedbackAcceptance::getProducerCode, code)
        .eq(FeedbackAcceptance::getProducerType, FeedbackAcceptanceEnums.PRODUCER_TYPE_ISOLATION.getCode())
        .in(FeedbackAcceptance::getResult, new ArrayList<>(Arrays.asList(
            FeedbackAcceptanceEnums.RESULT_UNREAD.getCode(),FeedbackAcceptanceEnums.RESULT_NOT_HANDLER.getCode()
        ))));
    if(feedbackAcceptanceCount > 0){
      throw new BusinessException(BusinessError.STUDENT_IN_UNDONE_FEEDBACK_ACCEPTANCE_ERROR);
    }
    //检查该学生是否处于隔离状态
    Long isolationPersonCount = isolationPersonMapper.selectCount(new LambdaQueryWrapper<IsolationPerson>()
        .eq(IsolationPerson::getCode, code)
        .ne(IsolationPerson::getState, IsolationPersonEnums.STATE_END.getCode()));
    if(isolationPersonCount > 0){
      throw new BusinessException(BusinessError.STUDENT_IN_UNDONE_ISOLATION_PERSON_ERROR);
    }
    //检查该学生是否存在未完成的请假单
    Long leaveCount = leaveMapper.selectCount(new LambdaQueryWrapper<Leave>()
        .eq(Leave::getCode, code)
        .eq(Leave::getIsReturn, LeaveEnums.IS_RETURN_NO.getCode())
        .ne(Leave::getApprovalResult, LeaveEnums.APPROVAL_RESULT_REJECT));
    if(leaveCount > 0){
      throw new BusinessException(BusinessError.STUDENT_IN_UNDONE_LEAVE_ERROR);
    }
  }

  /**
   * 删除学生相关操作的实施
   * @param code 学号
   */
  private void deleteStudentOperate(String code){
    //删除学生表
    getBaseMapper().delete(new LambdaQueryWrapper<Student>().eq(Student::getCode,code));
    //删除反馈受理表
    feedbackAcceptanceMapper.delete(new LambdaQueryWrapper<FeedbackAcceptance>()
        .eq(FeedbackAcceptance::getProducerCode,code));
    //删除寒暑假返校表
    holidayStreetMapper.delete(new LambdaQueryWrapper<HolidayStreet>()
        .eq(HolidayStreet::getCode,code));
    //删除隔离记录表
    isolationDetailMapper.delete(new LambdaQueryWrapper<IsolationDetail>()
        .eq(IsolationDetail::getCode,code));
    //删除隔离人员表
    isolationPersonMapper.delete(new LambdaQueryWrapper<IsolationPerson>()
        .eq(IsolationPerson::getCode,code));
    //删除请假表
    leaveMapper.delete(new LambdaQueryWrapper<Leave>()
        .eq(Leave::getCode,code));
    //获取该学生用户账号
    User user = userMapper.selectOne(
        new LambdaQueryWrapper<User>().eq(User::getAccount, code));
    //先删除用户和角色关联表
    userMapper.deleteUserToRole(user.getId());
    userMapper.delete(new LambdaQueryWrapper<User>().eq(User::getAccount,code));
  }

  /**
   * 增加学生账号的角色关联表
   */
  private void addStudentUser(Student student){
    User user = new User(
        student.getCode(),
        MD5.SysMd5(student.getCode(),userInitPassword),
        student.getPhone());
    user.setId(student.getId());
    userMapper.insert(user);
    userMapper.addUserRole(user.getId(), RoleEnums.STUDENT.getNumber());
  }

  /**
   * 验证学生必要字段是否相关联合法
   */
  private void checkStudentInformationMatch(LegitimateStudent command,Boolean isAdd){
    //验证院系是否存在
    if(0 == deptMapper.selectCount(new LambdaQueryWrapper<Dept>()
        .eq(Dept::getCode,command.getDeptCode()))){
      throw new BusinessException(BusinessError.DEPT_NOT_EXIST_ERROR);
    }
    //验证专业是否存在
    if(0 == majorMapper.selectCount(new LambdaQueryWrapper<Major>()
        .eq(Major::getCode,command.getMajorCode()))){
      throw new BusinessException(BusinessError.MAJOR_NOT_EXIST_ERROR);
    }
    //验证班级是否存在
    if(0 == classMapper.selectCount(new LambdaQueryWrapper<ClassEntity>()
        .eq(ClassEntity::getCode,command.getClassCode()))){
      throw new BusinessException(BusinessError.CLASS_NOT_EXIST_ERROR);
    }
    //验证学号、班级号、专业号、院系号是否相关联
    if(!command.getCode().substring(0,8).equals(command.getClassCode()) ||
        !command.getClassCode().substring(2,6).equals(command.getMajorCode()) ||
        !command.getMajorCode().substring(0,2).equals(command.getDeptCode())){
      throw new BusinessException(BusinessError.STUDENT_CODE_TO_DEPT_PARAMETER_ERROR);
    }
    //验证该学号是否被注册过
    if(isAdd){
      if(0 < getBaseMapper().selectCount(new LambdaQueryWrapper<Student>()
          .eq(Student::getCode,command.getCode()))){
        throw new BusinessException(BusinessError.STUDENT_CODE_EXIST_ERROR);
      }
    }
  }
}
