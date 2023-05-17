package xyz.chnytrcy.campusepidemicsystem.service.app.impl;

import static xyz.chnytrcy.campusepidemicsystem.model.constance.IsolationDetailConstance.DEAL_DAYS;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.chnytrcy.campusepidemicsystem.common.StudentCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationDetailMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationPersonMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentDailyMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.StudentMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.AddTemperatureCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.InsertDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail.QueryPageIsolationDetailCommand;
import xyz.chnytrcy.campusepidemicsystem.model.constance.IsolationDetailConstance;
import xyz.chnytrcy.campusepidemicsystem.model.dto.AbnormalStudentMessageDTO;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationDetail;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.entity.StudentDaily;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.StudentDailyEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.AddTemperatureVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.QueryIsolationTimeVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail.QueryPageIsolationDetailVO;
import xyz.chnytrcy.campusepidemicsystem.service.app.IsolationDetailAppService;
import xyz.chnytrcy.campusepidemicsystem.utils.mq.producter.StudentIsolateProducer;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.app.impl
 * @ClassName: IsolationDetailAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/13 3:27 PM
 * @Version: 1.0
 */
@Service
public class IsolationDetailAppServiceImpl extends ServiceImpl<IsolationDetailMapper, IsolationDetail>
    implements IsolationDetailAppService {

  @Autowired private IsolationPersonMapper isolationPersonMapper;

  @Autowired private StudentMapper studentMapper;

  @Autowired private StudentDailyMapper studentDailyMapper;

  @Autowired private StudentCommon studentCommon;

  @Autowired private StudentIsolateProducer studentIsolateProducer;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<AddTemperatureVO> addTemperature(AddTemperatureCommand command) {
    IsolationDetail isolationDetail = new IsolationDetail();
    isolationDetail.setTemperature(command.getTemperature());
    isolationDetail.setRelId(command.getId());
    IsolationPerson isolationPerson = isolationPersonMapper.selectOne(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getId, command.getId()));
    isolationDetail.setName(isolationPerson.getName());
    isolationDetail.setCode(isolationPerson.getCode());
    getBaseMapper().insert(isolationDetail);
    Boolean aBoolean = checkAbnormal(command.getId(),isolationPerson);
    return ResultFactory.successResult(new AddTemperatureVO(aBoolean));
  }

  @Override
  public Result<BasePageVO<QueryPageIsolationDetailVO>> queryPageIsolationDetail(
      QueryPageIsolationDetailCommand command) {
    String studentCode = studentCommon.getStudentCode();
    Long studentID = studentCommon.getStudentID();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<IsolationDetail> list = getBaseMapper().selectList(
        new LambdaQueryWrapper<IsolationDetail>()
            .eq(IsolationDetail::getCode, studentCode)
            .orderByDesc(IsolationDetail::getCreateTime));
    if(CollUtil.isEmpty(list)){
      return ResultFactory.successResult(new BasePageVO<>(null));
    }
    PageInfo pageInfo = new PageInfo(list);
    List<Integer> collect = list.stream().map(e -> e.getCreateTime().getDayOfYear()).collect(Collectors.toList());
    List<StudentDaily> studentDailies = studentDailyMapper.selectList(
        new LambdaQueryWrapper<StudentDaily>().eq(StudentDaily::getRelId, studentID));
    if(CollUtil.isEmpty(studentDailies)){
      return ResultFactory.successResult(new BasePageVO<>());
    }
    List<StudentDaily> filterStudentDailyList = studentDailies.stream()
        .filter(e -> collect.contains(e.getCreateTime().getDayOfYear()))
        .collect(Collectors.toList());
    Map<Integer,StudentDaily> integerStudentDailyList = Maps.newHashMap();
    filterStudentDailyList.forEach(e -> {
      integerStudentDailyList.put(e.getCreateTime().getDayOfYear(),e);
    });
    List<QueryPageIsolationDetailVO> key = Lists.newArrayList();
    list.forEach(e -> {
      QueryPageIsolationDetailVO convert = DozerUtils.convert(e, QueryPageIsolationDetailVO.class);
      int dayOfYear = convert.getCreateTime().getDayOfYear();
      Integer healthCode = integerStudentDailyList.get(dayOfYear).getHealthCode();
      if(ObjectUtil.isNull(healthCode)){
        healthCode = StudentDailyEnums.HEALTH_CODE_NULL.getCode();
      }
      convert.setHealthCode(healthCode);
      if(convert.getTemperature() >= IsolationDetailConstance.FEVER_TEMPERATURE){
        convert.setIsNormal(Boolean.TRUE);
      }else {
        convert.setIsNormal(Boolean.FALSE);
      }
      key.add(convert);
    });
    pageInfo.setList(key);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<Void> insertDetail(InsertDetailCommand command) {
    if(command.getPromise() == 1){
      throw new BusinessException(BusinessError.DAILY_PROMISE_ERROR);
    }
    Student student = studentCommon.getStudent();
    List<IsolationDetail> isolationDetails = getBaseMapper().selectList(new LambdaQueryWrapper<IsolationDetail>()
        .eq(IsolationDetail::getRelId, student.getId())
        .orderByDesc(IsolationDetail::getCreateTime)
        .last("limit 1"));
    if(CollUtil.isNotEmpty(isolationDetails)){
      LocalDateTime createTime = isolationDetails.get(0).getCreateTime();
      if(createTime.toLocalDate().equals(LocalDate.now())){
        throw new BusinessException(BusinessError.ISOLATION_DAILY_AGAIN_OPEN_CARD);
      }
    }
    IsolationDetail isolationDetail = new IsolationDetail();
    isolationDetail.setRelId(student.getId());
    isolationDetail.setCode(student.getCode());
    isolationDetail.setName(student.getName());
    isolationDetail.setTemperature(command.getTemperature());
    isolationDetail.setAbnormalSymptoms(command.getAbnormalSymptoms());
    isolationDetail.setHealthCode(command.getHealthCode());
    getBaseMapper().insert(isolationDetail);
    IsolationPerson isolationPerson = isolationPersonMapper.selectOne(
        new LambdaQueryWrapper<IsolationPerson>().eq(IsolationPerson::getRelId, student.getId()));
    checkAbnormal(student.getId(),isolationPerson);
    return ResultFactory.successResult();
  }

  @Override
  public Result<QueryIsolationTimeVO> queryIsolationTime() {
    String studentCode = studentCommon.getStudentCode();
    IsolationPerson isolationPerson = isolationPersonMapper.selectOne(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getCode, studentCode)
            .orderByDesc(IsolationPerson::getCreateTime)
            .last("limit 1"));
    QueryIsolationTimeVO key = DozerUtils.convert(isolationPerson, QueryIsolationTimeVO.class);
    return ResultFactory.successResult(key);
  }

  /**
   * 检查体温是否符合异常处理情况
   * @param command relId
   */
  public Boolean checkAbnormal(Long command,IsolationPerson isolationPerson) {
    List<IsolationDetail> list = getBaseMapper().selectList(
        new LambdaQueryWrapper<IsolationDetail>()
            .eq(IsolationDetail::getRelId, command)
            .ge(IsolationDetail::getTemperature, IsolationDetailConstance.FEVER_TEMPERATURE)
            .orderByDesc(IsolationDetail::getCreateTime)
            .last("limit 3"));
    //判断体温异常的天数是否小于3
    if(list.size() < DEAL_DAYS){
      return false;
    }
    //遍历最近三次体温异常的时是否为连续三天
    for (int i = 0; i < DEAL_DAYS - 1; i++) {
      if((list.get(i).getCreateTime().toLocalDate().toEpochDay() -
          list.get(i + 1).getCreateTime().toLocalDate().toEpochDay()) != 1){
        return false;
      }
    }
    //需要体温异常处理的情况
//    String name = list.get(0).getName();
//    String phone = studentMapper.selectOne(
//        new LambdaQueryWrapper<Student>()
//            .eq(Student::getId, isolationPerson.getRelId())).getPhone();
//    AbnormalStudentMessageDTO msg = new AbnormalStudentMessageDTO(name,phone);
//    studentIsolateProducer.abnormalMessage(msg);
    return true;
  }
}
