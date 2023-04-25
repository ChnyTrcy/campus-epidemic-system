package xyz.chnytrcy.campusepidemicsystem.service.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import xyz.chnytrcy.campusepidemicsystem.common.StreetCommon;
import xyz.chnytrcy.campusepidemicsystem.common.StudentCommon;
import xyz.chnytrcy.campusepidemicsystem.mapper.HolidayStreetMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.IsolationPersonMapper;
import xyz.chnytrcy.campusepidemicsystem.mapper.StreetMapper;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.street.BatchModifyStreetRiskLevelCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.street.ModifyStreetRiskLevelCommand;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.street.QueryPageStreetCommand;
import xyz.chnytrcy.campusepidemicsystem.model.constance.StreetConstance;
import xyz.chnytrcy.campusepidemicsystem.model.dto.AddMessageInfoDTO;
import xyz.chnytrcy.campusepidemicsystem.model.dto.HolidayStreetRiskLevelDTO;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Street;
import xyz.chnytrcy.campusepidemicsystem.model.entity.Student;
import xyz.chnytrcy.campusepidemicsystem.model.enums.BusinessError;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import xyz.chnytrcy.campusepidemicsystem.model.enums.entity.StreetEnums;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.street.QueryPageStreetVO;
import xyz.chnytrcy.campusepidemicsystem.model.vo.pc.street.QueryStatisticsStreetVO;
import xyz.chnytrcy.campusepidemicsystem.service.pc.StreetService;
import xyz.chnytrcy.campusepidemicsystem.utils.mq.producter.StudentIsolateProducer;
import xyz.chnytrcy.core.config.basic.model.BasePageVO;
import xyz.chnytrcy.core.config.exception.BusinessException;
import xyz.chnytrcy.core.utils.dozer.DozerUtils;
import xyz.chnytrcy.core.utils.result.Result;
import xyz.chnytrcy.core.utils.result.ResultFactory;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.service.pc.impl
 * @ClassName: StreetServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/24 4:55 PM
 * @Version: 1.0
 */
@Service
public class StreetServiceImpl extends ServiceImpl<StreetMapper, Street>
    implements StreetService {

  @Autowired private HolidayStreetMapper holidayStreetMapper;

  @Autowired private IsolationPersonMapper isolationPersonMapper;

  @Autowired private StudentCommon studentCommon;

  @Autowired private StreetCommon streetCommon;

  @Autowired private StudentIsolateProducer studentIsolateProducer;

  @Override
  public Result<BasePageVO<QueryPageStreetVO>> queryPageStreet(QueryPageStreetCommand command) {
    int length;
    if(null == command.getCode() || StringUtils.isBlank(command.getCode())){
      length = 0;
    }else {
      length = command.getCode().length();
    }
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<Street> list = getBaseMapper().queryPageStreet(command,length);
    PageInfo pageInfo = new PageInfo(list);
    List<QueryPageStreetVO> key = DozerUtils.convertList(list,
        QueryPageStreetVO.class);
    pageInfo.setList(key);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<Void> modifyStreetRiskLevel(ModifyStreetRiskLevelCommand command) {
    Street street = getBaseMapper().selectOne(new LambdaQueryWrapper<Street>()
        .eq(Street::getCode, command.getStreetCode()));
    //街道不存在
    if(null == street){
      throw new BusinessException(BusinessError.STREET_IS_NOT_EXIST_ERROR);
    }
    //街道修改后的风险等级与原来一样
    if(Objects.equals(street.getRiskLevel(), command.getRiskLevel())){
      throw new BusinessException(BusinessError.STREET_SAME_AS_ORIGINAL_ERROR);
    }
    //修改为中高风险
    if(!StreetEnums.RISK_LEVEL_LOW.getNumber().equals(command.getRiskLevel())){
      this.modifyStreetToMediumOrHighRisk(command);
    }
    street.setRiskLevel(command.getRiskLevel());
    getBaseMapper().update(street,new LambdaQueryWrapper<Street>()
        .eq(Street::getCode,street.getCode()));
    return ResultFactory.successResult();
  }

  @Override
  public Result<Void> batchModifyStreetRiskLevel(BatchModifyStreetRiskLevelCommand command) {
    command.getList().forEach(this::modifyStreetRiskLevel);
    return ResultFactory.successResult();
  }

  @Override
  public Result<QueryStatisticsStreetVO> queryStatisticsStreet() {
    Integer high = getBaseMapper().countHighStreet();
    Integer mid = getBaseMapper().countMidStreet();
    QueryStatisticsStreetVO key = new QueryStatisticsStreetVO(
        high,mid,StreetConstance.sumNum - high - mid
    );
    return ResultFactory.successResult(key);
  }

  /**
   * 修改地区为中高风险,异步执行
   */
  @Async
  public void modifyStreetToMediumOrHighRisk(ModifyStreetRiskLevelCommand command){
    //1、获得涉及风险地区的学生
    List<HolidayStreetRiskLevelDTO> riskLevelDTOList = holidayStreetMapper.queryHolidayToRiskLevel(command);
    if(riskLevelDTOList.isEmpty()){
      return;
    }

    HashMap<String,HolidayStreetRiskLevelDTO> hashMap = new HashMap<>();
    riskLevelDTOList.forEach(e -> hashMap.put(e.getCode(),e));

    //2、获得涉案学生并且正在被隔离的名单
    List<String> isExistStudentList = isolationPersonMapper.queryStudentIsolationList(riskLevelDTOList);

    HashSet<String> collect = riskLevelDTOList.stream()
        .map(HolidayStreetRiskLevelDTO::getCode)
        .collect(Collectors.toCollection(HashSet::new));

    //3、过滤已经被隔离的学生
    isExistStudentList.forEach(collect::remove);

    //获得学生映射表
    HashMap<String, Student> studentHashMap = new HashMap<>(studentCommon.studentHashMap());

    //获得街道映射表（开发环境不执行）
    HashMap<String,String> streetMap = new HashMap<>(streetCommon.codeToString());

    //4、添加到隔离人员表
    collect.forEach(e -> {
      IsolationPerson entity = new IsolationPerson();
      entity.setCode(e);
      entity.setRelId(studentHashMap.get(e).getId());
      entity.setName(studentHashMap.get(e).getName());
      entity.setDeptCode(studentHashMap.get(e).getDeptCode());
      entity.setDeptName(studentHashMap.get(e).getDeptName());
      entity.setMajorCode(studentHashMap.get(e).getMajorCode());
      entity.setMajorName(studentHashMap.get(e).getMajorName());
      entity.setClassCode(studentHashMap.get(e).getClassCode());
      entity.setClassName(studentHashMap.get(e).getClassName());
      entity.setState(IsolationPersonEnums.STATE_NOTIFICATION_NOT_QUARANTINED.getCode());
      //5、添加隔离人员
      isolationPersonMapper.insert(entity);

      //6、短信通知(开发环境不执行)
      AddMessageInfoDTO addMessageInfoDTO = new AddMessageInfoDTO();
      addMessageInfoDTO.setName(studentHashMap.get(e).getName());
      addMessageInfoDTO.setStreetName(streetMap.get(command.getStreetCode()));
      addMessageInfoDTO.setRiskLevel(command.getRiskLevel());
      addMessageInfoDTO.setPhone(studentHashMap.get(e).getPhone());
      studentIsolateProducer.addMessage(addMessageInfoDTO);
    });
  }
}
