package chnytrcy.xyz.campusepidemicsystem.service.pc.impl;

import chnytrcy.xyz.campusepidemicsystem.common.DeptCommon;
import chnytrcy.xyz.campusepidemicsystem.config.annotation.DataSynchronous;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.config.shiro.utils.HttpContextUtil;
import chnytrcy.xyz.campusepidemicsystem.mapper.AdminMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.DeptMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.FeedbackAcceptanceMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationPersonMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.TeacherMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.UserMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.AddTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.DeleteTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.QueryEpidemicPersonCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.QueryTeacherPageCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.SetTeacherToEpidemicPersonCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacher.UpdateTeacherCommand;
import chnytrcy.xyz.campusepidemicsystem.model.dto.StatisticalNameCountDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Admin;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Dept;
import chnytrcy.xyz.campusepidemicsystem.model.entity.FeedbackAcceptance;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Teacher;
import chnytrcy.xyz.campusepidemicsystem.model.entity.user.User;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.EntityEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.FeedbackAcceptanceEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.RoleEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.TeacherEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis.CountDeptEpidemicNumVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacher.QueryEpidemicPersonVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.teacher.QueryTeacherPageVO;
import chnytrcy.xyz.campusepidemicsystem.service.pc.TeacherService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.ErrorEntity;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo.TeacherBO;
import chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.listener.TeacherListener;
import chnytrcy.xyz.campusepidemicsystem.utils.md5.MD5;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import cn.hutool.core.collection.CollUtil;
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.pc.impl
 * @ClassName: TeacherServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 4:58 PM
 * @Version: 1.0
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher>
    implements TeacherService {

  @Autowired private DeptMapper deptMapper;

  @Autowired private FeedbackAcceptanceMapper feedbackAcceptanceMapper;

  @Autowired private UserMapper userMapper;

  @Autowired private IsolationPersonMapper isolationPersonMapper;

  @Autowired private AdminMapper adminMapper;

  @Autowired private DeptCommon deptCommon;

  @Autowired private HttpContextUtil httpContextUtil;

  @Autowired private TeacherListener teacherListener;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> addTeacher(AddTeacherCommand command) {
    //判断院系是否存在
    if(0 == deptMapper.selectCount(new LambdaQueryWrapper<Dept>()
        .eq(Dept::getCode,command.getDeptCode()))){
      throw new BusinessException(BusinessError.DEPT_NOT_EXIST_ERROR);
    }
    //判断工号是否已存在
    if(0 < getBaseMapper().selectCount(new LambdaQueryWrapper<Teacher>().eq(Teacher::getCode,command.getCode()))){
      throw new BusinessException(BusinessError.TEACHER_CODE_IS_EXIST_ERROR);
    }
    Teacher teacher = DozerUtils.convert(command, Teacher.class);
    teacher.setDeptName(deptCommon.deptHashMap().get(command.getDeptCode()));
    getBaseMapper().insert(teacher);
    //创建用户
    User user = new User();
    user.setId(teacher.getId());
    user.setAccount(teacher.getCode());
    user.setPassword(MD5.SysMd5(teacher.getCode(),"123456"));
    user.setPhone(teacher.getPhone());
    userMapper.insert(user);
    userMapper.addUserRole(user.getId(), RoleEnums.TEACHER.getNumber());
    return ResultFactory.successResult();
  }

  @Override
  public Result<BasePageVO<QueryTeacherPageVO>> queryTeacherPage(QueryTeacherPageCommand command) {
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<Teacher> teacherList = getBaseMapper().queryTeacherPage(command);
    PageInfo pageInfo = new PageInfo(teacherList);
    List<QueryTeacherPageVO> queryTeacherPageVOS = DozerUtils.convertList(teacherList,
        QueryTeacherPageVO.class);
    pageInfo.setList(queryTeacherPageVOS);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  @DataSynchronous(type = EntityEnums.TEACHER)
  public Result<Void> updateTeacher(UpdateTeacherCommand command) {
    //判断院系是否存在
    if(0 == deptMapper.selectCount(new LambdaQueryWrapper<Dept>()
        .eq(Dept::getCode,command.getDeptCode()))){
      throw new BusinessException(BusinessError.DEPT_NOT_EXIST_ERROR);
    }
    Teacher teacher = DozerUtils.convert(command, Teacher.class);
    getBaseMapper().updateById(teacher);
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  @DataSynchronous(type = EntityEnums.TEACHER)
  public Result<Void> deleteTeacher(DeleteTeacherCommand command) {
    //判断是否为防疫人员
    if(Boolean.TRUE.equals(this.checkTeacherIsEpidemic(command.getCode()))){
      this.deleteTeacherCheck(command.getCode());
    }
    getBaseMapper().delete(new LambdaQueryWrapper<Teacher>()
        .eq(Teacher::getCode,command.getCode()));
    Long id = userMapper.selectOne(new LambdaQueryWrapper<User>()
        .eq(User::getAccount, command.getCode())).getId();
    userMapper.deleteUserToRole(id);
    userMapper.deleteById(id);
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> setTeacherToEpidemicPerson(SetTeacherToEpidemicPersonCommand command) {
    Teacher one = getBaseMapper().selectOne(new LambdaQueryWrapper<Teacher>()
        .eq(Teacher::getCode, command.getCode()));
    Integer epidemicMark = one.getEpidemicMark();
    if(Objects.equals(epidemicMark, TeacherEnums.EPIDEMIC_MARK_NO.getNumber())){
      one.setEpidemicMark(TeacherEnums.EPIDEMIC_MARK_YES.getNumber());
      //更新教职工表
      getBaseMapper().update(one,new LambdaQueryWrapper<Teacher>()
          .eq(Teacher::getCode,command.getCode()));
      //更新用户权限表
      userMapper.updateRoleByUserId(one.getId(), RoleEnums.EPIDEMIC_PREVENTION.getNumber());
    }else {
      //解除防疫人员属性
      //检查相关属性
      this.deleteTeacherCheck(command.getCode());
      one.setEpidemicMark(TeacherEnums.EPIDEMIC_MARK_NO.getNumber());
      //更新教职工表
      getBaseMapper().update(one,new LambdaQueryWrapper<Teacher>()
          .eq(Teacher::getCode,command.getCode()));
      //更新用户权限表
      userMapper.updateRoleByUserId(one.getId(),RoleEnums.TEACHER.getNumber());
    }
    return ResultFactory.successResult();
  }

  @Override
  public Result<BasePageVO<QueryEpidemicPersonVO>> queryEpidemicPerson(QueryEpidemicPersonCommand command) {
    Admin admin = adminMapper.selectOne(new LambdaQueryWrapper<Admin>()
        .eq(Admin::getUserId, httpContextUtil.getUserId()));
    String deptCode = admin.getDeptCode();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<Teacher> list =  getBaseMapper().queryEpidemicPerson(command.getWordType(),
        command.getKeyword(),
        deptCode);
    PageInfo pageInfo = new PageInfo(list);
    List<QueryEpidemicPersonVO> vos = DozerUtils.convertList(list,
        QueryEpidemicPersonVO.class);
    pageInfo.setList(vos);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<CountDeptEpidemicNumVO> countDeptEpidemicNum() {
    List<Teacher> teacherList = list(
        new LambdaQueryWrapper<Teacher>().eq(Teacher::getEpidemicMark,
            TeacherEnums.EPIDEMIC_MARK_YES.getCode()));
    Map<String, List<Teacher>> map = teacherList.stream()
        .collect(Collectors.groupingBy(Teacher::getDeptName));
    List<StatisticalNameCountDTO> list = Lists.newArrayList();
    for (Entry<String, List<Teacher>> entry : map.entrySet()) {
      StatisticalNameCountDTO statisticalNameCountDTO = new StatisticalNameCountDTO(entry.getKey(),entry.getValue().size());
      list.add(statisticalNameCountDTO);
    }
    return ResultFactory.successResult(new CountDeptEpidemicNumVO(list));
  }

  @Override
  public void downloadTemplate(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    InputStream resourceAsStream = this.getClass().getClassLoader()
        .getResourceAsStream("excelTemplates/teacherTemplate.xlsx");
    OutputStream outputStream = response.getOutputStream();
    response.setContentType("application/x-download");
    response.addHeader("Content-Disposition", "attachment;filename=studentTemplate.xlsx");
    IOUtils.copy(resourceAsStream, outputStream);
    outputStream.flush();
  }

  @Override
  public Result uploadAndParseTemplate(MultipartFile file) throws IOException {
    InputStream inputStream = file.getInputStream();
    EasyExcelFactory
        .read(inputStream, TeacherBO.class, teacherListener)
        .sheet(0)
        .headRowNumber(2)
        .doReadSync();
    List<ErrorEntity> errorList = teacherListener.getErrorList();
    if(CollUtil.isEmpty(errorList)){
      return ResultFactory.successResult();
    }else {
      return ResultFactory.warningResult(errorList);
    }
  }

  /**
   * 判断该老师是不是防疫人员
   * @param code 工号
   */
  private Boolean checkTeacherIsEpidemic(String code){
    return 1 <= getBaseMapper().selectCount(new LambdaQueryWrapper<Teacher>()
        .eq(Teacher::getEpidemicMark, TeacherEnums.EPIDEMIC_MARK_YES)
        .eq(Teacher::getCode,code));
  }

  /**
   * 删除该教职工的检查
   */
  private void deleteTeacherCheck(String code){
    //检查是否有未完成的反馈受理表
    Long aLong = feedbackAcceptanceMapper.selectCount(new LambdaQueryWrapper<FeedbackAcceptance>()
        .eq(FeedbackAcceptance::getProducerType,
            FeedbackAcceptanceEnums.PRODUCER_TYPE_EPIDEMIC_PREVENTION)
        .eq(FeedbackAcceptance::getProducerCode, code)
        .in(FeedbackAcceptance::getResult, new ArrayList<>(Arrays.asList(
            FeedbackAcceptanceEnums.RESULT_UNREAD, FeedbackAcceptanceEnums.RESULT_NOT_HANDLER
        ))));
    if(0 < aLong){
      throw new BusinessException(BusinessError.TEACHER_IN_UNDONE_FEEDBACK_ACCEPTANCE_ERROR);
    }
    //检查是否有自己名下的隔离学生
    Long aLong1 = isolationPersonMapper.selectCount(new LambdaQueryWrapper<IsolationPerson>()
        .eq(IsolationPerson::getPreventionPersonnelCode, code)
        .eq(IsolationPerson::getState, IsolationPersonEnums.STATE_QUARANTINED));
    if(0 < aLong1){
      throw new BusinessException(BusinessError.TEACHER_IN_UNDONE_ISOLATION_STUDENT_ERROR);
    }
  }

}
