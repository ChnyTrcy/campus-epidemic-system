package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.chnytrcy.campusepidemicsystem.common.TeacherCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.AdminMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.DeptMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationDetailMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationPersonMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.LeaveMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.TeacherMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.UserMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.IsolationCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageAdminIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.QueryPageToBeIsolationPersonCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson.ReleaseQuarantineCommand;
import xyz.chnytrcy.campusepidemicsystem.model.constance.AnalysisConstance;
import xyz.chnytrcy.campusepidemicsystem.model.constance.IsolationPersonConstance;
import xyz.chnytrcy.campusepidemicsystem.model.dto.CountPeopleDistributionDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.StatisticalDateCountDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.StatisticalNameCountDTO;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Admin;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Dept;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationDetail;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Leave;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Teacher;
import xyz.chnytrcy.campusepidemicsystem.model.entity.user.User;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.RoleEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.TeacherEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis.CountDeptPeopleProportionVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis.CountNewIsolationListVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis.CountPeopleDistributionVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryAdminIsolationPersonAnalysisVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageAdminIsolationPersonVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageIsolationPersonVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson.QueryPageToBeIsolationPersonVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.IsolationPersonService;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.config.shiro.utils.HttpContextUtil;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
 * @ClassName: IsolationPersonServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 4:37 PM
 * @Version: 1.0
 */
@Service
public class IsolationPersonServiceImpl extends ServiceImpl<IsolationPersonMapper, IsolationPerson>
    implements IsolationPersonService {

  @Autowired private HttpContextUtil httpContextUtil;

  @Autowired private AdminMapper adminMapper;

  @Autowired private IsolationDetailMapper isolationDetailMapper;

  @Autowired private UserMapper userMapper;

  @Autowired private LeaveMapper leaveMapper;

  @Autowired private DeptMapper deptMapper;

  @Autowired private TeacherMapper teacherMapper;

  @Autowired private TeacherCommon teacherCommon;

  @Override
  public Result<BasePageVO<QueryPageAdminIsolationPersonVO>> queryPageAdminIsolationPerson(
      QueryPageAdminIsolationPersonCommand command) {
    Admin admin = adminMapper.selectOne(new LambdaQueryWrapper<Admin>()
        .eq(Admin::getUserId, httpContextUtil.getUserId()));
    if(ObjectUtil.isNull(admin)){
      throw new BusinessException(BusinessError.ADMIN_IS_NOT_EXIST_ERROR);
    }
    String deptCode = admin.getDeptCode();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<IsolationPerson> list = getBaseMapper().queryPageIsolationPersonByAdmin(
        command.getKeyword(),
        command.getWordType(),
        command.getState(),
        deptCode
        );
    PageInfo pageInfo = new PageInfo(list);
    List<QueryPageAdminIsolationPersonVO> vos = DozerUtils.convertList(list,
        QueryPageAdminIsolationPersonVO.class);
    pageInfo.setList(vos);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<BasePageVO<QueryPageToBeIsolationPersonVO>> queryPageToBeIsolationPerson(
      QueryPageToBeIsolationPersonCommand command) {
    String teacherDeptCode = teacherCommon.getTeacherDeptCode();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<IsolationPerson> list = getBaseMapper().queryPageToBeIsolationPerson(command,teacherDeptCode);
    PageInfo pageInfo = new PageInfo(list);
    List<QueryPageToBeIsolationPersonVO> vos = DozerUtils.convertList(
        list, QueryPageToBeIsolationPersonVO.class);
    pageInfo.setList(vos);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<BasePageVO<QueryPageIsolationPersonVO>> queryPageIsolationPerson(
      QueryPageIsolationPersonCommand command) {
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    String teacherDeptCode = teacherCommon.getTeacherDeptCode();
    List<QueryPageIsolationPersonVO> list = getBaseMapper().queryPageIsolationPerson(
        command,
        teacherDeptCode
    );
    PageInfo pageInfo = new PageInfo(list);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> isolation(IsolationCommand command) {
    Teacher epidemic = teacherCommon.getEpidemic();
    IsolationPerson isolationPerson = getBaseMapper().selectOne(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getId, command.getId())
            .orderByDesc(IsolationPerson::getCreateTime)
            .last("limit 1"));
    this. isolationDeal(isolationPerson,epidemic);
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> releaseQuarantine(ReleaseQuarantineCommand command) {
    IsolationPerson isolationPerson = getBaseMapper().selectById(command.getId());
    if(Objects.isNull(isolationPerson)){
      throw new BusinessException(BusinessError.ISOLATION_PERSON_IS_NOT_EXIST_ERROR);
    }
    if(!isolationPerson.getState().equals(IsolationPersonEnums.STATE_QUARANTINED.getCode())){
      throw new BusinessException(BusinessError.ISOLATION_PERSON_NOT_STATE_QUARANTINED_ERROR);
    }
    List<IsolationDetail> list = isolationDetailMapper.selectList(
        new LambdaQueryWrapper<IsolationDetail>()
            .eq(IsolationDetail::getRelId, isolationPerson.getId()));
    if(list.size() < IsolationPersonConstance.QUARANTINE_DAYS || !isolationPerson.getEndTime().isAfter(LocalDateTime.now())){
      throw new BusinessException(BusinessError.ISOLATION_DETAIL_COUNT_LESS_ERROR);
    }
    isolationPerson.setState(IsolationPersonEnums.STATE_END.getCode());
    getBaseMapper().updateById(isolationPerson);
    //更新权限表
    User user = userMapper.selectOne(
        new LambdaQueryWrapper<User>()
            .eq(User::getAccount, isolationPerson.getCode()));
    userMapper.updateRoleByUserId(user.getId(),RoleEnums.STUDENT.getNumber());
    return ResultFactory.successResult();
  }

  @Override
  public Result<QueryAdminIsolationPersonAnalysisVO> queryAdminIsolationPersonAnalysis() {
    String deptCode = teacherCommon.getEpidemicDeptCode();
    List<IsolationPerson> isolationPersonList = getBaseMapper().selectList(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getDeptCode, deptCode));
    int notificationNotQuarantined = 0,isolated = 0,treat = 0,end = 0;
    for (IsolationPerson isolationPerson : isolationPersonList) {
      switch (isolationPerson.getState()){
        case 0:notificationNotQuarantined++;break;
        case 1:isolated++;break;
        case 2:treat++;break;
        case 3:end++;break;
        default:break;
      }
    }
    QueryAdminIsolationPersonAnalysisVO key = new QueryAdminIsolationPersonAnalysisVO(
        isolated,notificationNotQuarantined,treat,end);
    return ResultFactory.successResult(key);
  }

  @Override
  public Result<CountPeopleDistributionVO> countPeopleDistribution() {
    CountPeopleDistributionVO key = new CountPeopleDistributionVO();
    List<IsolationPerson> isolationPersonList = getBaseMapper().selectList(null);
    CountPeopleDistributionDTO allIsolation = allIsolation(isolationPersonList);
    long now = allIsolation.getNow();
    long yesterday = allIsolation.getYesterday();
    long y = yesterday == 0 ? 1 : yesterday;
    int allNumPercent = (int) (Math.abs(now - yesterday) * 100 / y);
    key.setAllIsolationNum((int) now);
    key.setAllIsolationPercent(allNumPercent);
    key.setAllIsolationPositive(now >= yesterday ? Boolean.TRUE : Boolean.FALSE);
    CountPeopleDistributionDTO newIsolation = newIsolation(isolationPersonList);
    long newIsolationNow = newIsolation.getNow();
    long newIsolationYesterday = newIsolation.getYesterday();
    long t = newIsolationYesterday == 0 ? 1 : newIsolationYesterday;
    int newIsolationPercent = (int) (Math.abs(newIsolationNow - newIsolationYesterday) * 100 / t);
    key.setNewIsolationNum((int) newIsolationNow);
    key.setNewIsolationPercent(newIsolationPercent);
    key.setNewIsolationPositive(newIsolationNow >= newIsolationYesterday ? Boolean.TRUE : Boolean.FALSE);
    CountPeopleDistributionDTO removeIsolation = removeIsolation(isolationPersonList);
    long removeIsolationNow = removeIsolation.getNow();
    long removeIsolationYesterday = removeIsolation.getYesterday();
    long r = removeIsolationYesterday == 0 ? 1 : removeIsolationYesterday;
    int removeIsolationPercent = (int) (Math.abs(removeIsolationNow - removeIsolationYesterday) * 100 / r);
    key.setNewRemoveNum((int)removeIsolationNow);
    key.setNewRemovePercent(removeIsolationPercent);
    key.setNewRemovePositive(removeIsolationNow >= removeIsolationYesterday ? Boolean.TRUE : Boolean.FALSE);
    CountPeopleDistributionDTO leaveCount = leaveCount();
    long leaveCountYesterday = leaveCount.getYesterday();
    long beforeYesterday = leaveCount.getBeforeYesterday();
    long b = beforeYesterday == 0 ? 1 : beforeYesterday;
    int leavePercent = (int)(Math.abs(leaveCountYesterday - beforeYesterday) * 100 / b);
    key.setLeaveYesterdayNum((int) leaveCountYesterday);
    key.setLeaveYesterdayPercent(leavePercent);
    key.setLeaveYesterdayPositive(leaveCountYesterday >= beforeYesterday ? Boolean.TRUE : Boolean.FALSE);
    return ResultFactory.successResult(key);
  }

  @Override
  public Result<CountDeptPeopleProportionVO> countDeptPeopleProportion() {
    List<Dept> deptList = deptMapper.selectList(null);
    Map<String,Integer> hashmap = Maps.newHashMap();
    List<String> deptNameAllList = deptList.stream().map(Dept::getName).collect(Collectors.toList());
    List<Teacher> teacherList = teacherMapper.selectList(null);
    Map<String, List<Teacher>> collect = teacherList.stream()
        .filter(e -> e.getEpidemicMark().equals(TeacherEnums.EPIDEMIC_MARK_YES.getNumber()))
        .collect(Collectors.groupingBy(Teacher::getDeptName));
    for (Entry<String, List<Teacher>> entry : collect.entrySet()) {
      hashmap.put(entry.getKey(),entry.getValue().size());
    }
    List<StatisticalNameCountDTO> statisticalNameCountDTOS = fillNameCountMapData(deptNameAllList,hashmap);
    Map<String,Integer> isolationPersonHashMap = Maps.newHashMap();
    List<IsolationPerson> isolationPersonList = getBaseMapper().selectList(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getState,IsolationPersonEnums.STATE_QUARANTINED.getCode()));
    Map<String, List<IsolationPerson>> isolationMap = isolationPersonList.stream()
        .collect(Collectors.groupingBy(IsolationPerson::getDeptName));
    for (Entry<String, List<IsolationPerson>> entry : isolationMap.entrySet()) {
      isolationPersonHashMap.put(entry.getKey(),entry.getValue().size());
    }
    List<StatisticalNameCountDTO> isolationPersonDTOS = fillNameCountMapData(deptNameAllList,
        isolationPersonHashMap);
    return ResultFactory.successResult(new CountDeptPeopleProportionVO(statisticalNameCountDTOS,isolationPersonDTOS));
  }

  @Override
  public Result<CountNewIsolationListVO> countNewIsolationList() {
    LocalDateTime firstDay = LocalDateTime.now().minusDays(AnalysisConstance.SEVEN_DAY)
        .withHour(0).withMinute(0).withSecond(0).withNano(0);
    LocalDate now = LocalDateTime.now().toLocalDate();
    List<IsolationPerson> list = list(
        new LambdaQueryWrapper<IsolationPerson>()
            .ge(IsolationPerson::getStartTime,firstDay));
    Map<Integer,Integer> map = Maps.newHashMap();
    list.forEach(e -> {
      LocalDate localDate = e.getStartTime().toLocalDate();
      int days = Period.between(localDate, now).getDays();
      if(map.containsKey(days)){
        map.put(days,map.get(days) + 1);
      }else {
        map.put(days,1);
      }
    });
    List<StatisticalDateCountDTO> key = Lists.newArrayList();
    for (int i = 0; i < 7; i++) {
      LocalDate localDate = now.minusDays(i);
      String s = localDate.getMonthValue() + "-" + localDate.getDayOfMonth();
      StatisticalDateCountDTO statisticalDateCountDTO = new StatisticalDateCountDTO(
          s, ObjectUtil.isNull(map.get(i)) ? 0 : map.get(i));
      key.add(statisticalDateCountDTO);
    }
    return ResultFactory.successResult(new CountNewIsolationListVO(key));
  }

  private CountPeopleDistributionDTO allIsolation(List<IsolationPerson> data){
    long now = data.stream().filter(e -> {
          int dayOfYear = LocalDateTime.now().getDayOfYear();
          int start = e.getStartTime().getDayOfYear();
          int end = e.getEndTime().getDayOfYear();
          return start <= dayOfYear && end >= dayOfYear;
        })
        .count();
    long yesterday = data.stream().filter(e -> {
      int dayOfYear = LocalDateTime.now().minusDays(AnalysisConstance.YESTERDAY).getDayOfYear();
      int start = e.getStartTime().getDayOfYear();
      int end = e.getEndTime().getDayOfYear();
      return start <= dayOfYear && end >= dayOfYear;
    }).count();
    return new CountPeopleDistributionDTO(now,yesterday);
  }

  private CountPeopleDistributionDTO newIsolation(List<IsolationPerson> data){
    long now = data.stream().filter(e -> {
      int dayOfYear = LocalDateTime.now().getDayOfYear();
      return e.getStartTime().getDayOfYear() == dayOfYear;
    }).count();
    long yesterday = data.stream().filter(e -> {
      int yesterdayOfYear = LocalDateTime.now().minusDays(AnalysisConstance.YESTERDAY).getDayOfYear();
      return e.getStartTime().getDayOfYear() == yesterdayOfYear;
    }).count();
    return new CountPeopleDistributionDTO(now,yesterday);
  }

  private CountPeopleDistributionDTO removeIsolation(List<IsolationPerson> data){
    long now = data.stream().filter(e -> {
      int dayOfYear = LocalDateTime.now().getDayOfYear();
      return e.getEndTime().getDayOfYear() == dayOfYear;
    }).count();
    long yesterday = data.stream().filter(e -> {
      int yesterdayOfYear = LocalDateTime.now().minusDays(AnalysisConstance.YESTERDAY).getDayOfYear();
      return e.getEndTime().getDayOfYear() == yesterdayOfYear;
    }).count();
    return new CountPeopleDistributionDTO(now,yesterday);
  }

  private CountPeopleDistributionDTO leaveCount(){
    List<Leave> data = leaveMapper.selectList(null);
    long count = data.stream().filter(e -> {
      int dayOfYear = LocalDateTime.now().minusDays(AnalysisConstance.YESTERDAY).getDayOfYear();
      return e.getEstimateStartTime().getDayOfYear() == dayOfYear;
    }).count();
    long count1 = data.stream().filter(e -> {
      int dayOfYear = LocalDateTime.now().minusDays(AnalysisConstance.BEFORE_YESTERDAY)
          .getDayOfYear();
      return e.getEstimateStartTime().getDayOfYear() == dayOfYear;
    }).count();
    CountPeopleDistributionDTO key = new CountPeopleDistributionDTO();
    key.setYesterday(count);
    key.setBeforeYesterday(count1);
    return key;
  }

  public void isolationDeal(IsolationPerson isolationPerson,Teacher epidemic){
    //隔离人员不存在的情况
    if(Objects.isNull(isolationPerson)){
      throw new BusinessException(BusinessError.ISOLATION_PERSON_IS_NOT_EXIST_ERROR);
    }
    //检查该隔离人员没有过往未完成隔离的记录
    List<IsolationPerson> isolationPersonList = getBaseMapper().selectList(
        new LambdaQueryWrapper<IsolationPerson>()
            .in(IsolationPerson::getState, Arrays.asList(
                IsolationPersonEnums.STATE_QUARANTINED.getNumber(),
                IsolationPersonEnums.STATE_TREAT.getNumber()))
            .eq(IsolationPerson::getCode, isolationPerson.getCode()));
    if(!isolationPersonList.isEmpty()){
      throw new BusinessException(BusinessError.ISOLATION_PERSON_HAVING_UNDONE_ERROR);
    }
    //确保该隔离人员处于已通知未隔离状态
    if(!IsolationPersonEnums.STATE_NOTIFICATION_NOT_QUARANTINED.getCode().equals(isolationPerson.getState())){
      throw new BusinessException(BusinessError.ISOLATION_PERSON_STATE_NOT_EQUALS_ZERO_ERROR);
    }
    //修改记录
    isolationPerson.setState(IsolationPersonEnums.STATE_QUARANTINED.getCode());
    isolationPerson.setStartTime(LocalDateTime.now());
    isolationPerson.setEndTime(LocalDateTime.now().plusDays(IsolationPersonConstance.QUARANTINE_DAYS));
    isolationPerson.setPreventionPersonnelCode(epidemic.getCode());
    isolationPerson.setPreventionPersonnelName(epidemic.getName());
    getBaseMapper().update(isolationPerson,
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getId,isolationPerson.getId()));

    //将该学生用户类型改为隔离人员
    User user = userMapper.selectOne(
        new LambdaQueryWrapper<User>()
            .eq(User::getAccount, isolationPerson.getCode()));
    userMapper.updateRoleByUserId(user.getId(), RoleEnums.QUARANTINE.getNumber());
  }

  private List<StatisticalNameCountDTO> fillNameCountMapData(List<String> allList, Map<String,Integer> data){
    List<StatisticalNameCountDTO> key = Lists.newArrayList();
    allList.forEach(e -> {
      key.add(new StatisticalNameCountDTO(e, data.getOrDefault(e, 0)));
    });
    return key;
  }

}
