package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import static chnytrcy.xyz.campusepidemicsystem.model.constance.IsolationDetailConstance.DEAL_DAYS;

import chnytrcy.xyz.campusepidemicsystem.common.StudentCommon;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationPersonMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentDailyMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail.AddTemperatureCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.isolationdetail.QueryPageIsolationDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.constance.IsolationDetailConstance;
import chnytrcy.xyz.campusepidemicsystem.model.dto.AbnormalStudentMessageDTO;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationDetail;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.entity.StudentDaily;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.StudentDailyEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationdetail.AddTemperatureVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationdetail.QueryPageIsolationDetailVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.IsolationDetailAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.mq.producter.StudentIsolateProducer;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
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
    PageInfo pageInfo = new PageInfo(list);
    List<Integer> collect = list.stream().map(e -> e.getCreateTime().getDayOfYear()).collect(Collectors.toList());
    List<StudentDaily> studentDailies = studentDailyMapper.selectList(
        new LambdaQueryWrapper<StudentDaily>().eq(StudentDaily::getRelId, studentID));
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
    String name = list.get(0).getName();
    String phone = studentMapper.selectOne(
        new LambdaQueryWrapper<Student>()
            .eq(Student::getId, isolationPerson.getRelId())).getPhone();
    AbnormalStudentMessageDTO msg = new AbnormalStudentMessageDTO(name,phone);
    studentIsolateProducer.abnormalMessage(msg);
    return true;
  }
}
