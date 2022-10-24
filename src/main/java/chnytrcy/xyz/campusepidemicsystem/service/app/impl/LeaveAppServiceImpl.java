package chnytrcy.xyz.campusepidemicsystem.service.app.impl;

import chnytrcy.xyz.campusepidemicsystem.common.StudentCommon;
import chnytrcy.xyz.campusepidemicsystem.common.UserCommon;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageVO;
import chnytrcy.xyz.campusepidemicsystem.config.exception.BusinessException;
import chnytrcy.xyz.campusepidemicsystem.mapper.AdminMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.IsolationPersonMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveDetailMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveHealthMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.LeaveMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.StreetMapper;
import chnytrcy.xyz.campusepidemicsystem.mapper.StudentMapper;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.AddNewLeaveApplicationCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.BackSchoolCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.QueryHistoryListCommand;
import chnytrcy.xyz.campusepidemicsystem.model.command.app.leave.QueryLeaveDetailCommand;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Admin;
import chnytrcy.xyz.campusepidemicsystem.model.entity.IsolationPerson;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Leave;
import chnytrcy.xyz.campusepidemicsystem.model.entity.LeaveDetail;
import chnytrcy.xyz.campusepidemicsystem.model.entity.LeaveHealth;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Street;
import chnytrcy.xyz.campusepidemicsystem.model.entity.Student;
import chnytrcy.xyz.campusepidemicsystem.model.enums.BusinessError;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.IsolationPersonEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveDetailEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.LeaveHealthEnums;
import chnytrcy.xyz.campusepidemicsystem.model.enums.entity.StudentEnums;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.LeaveDetailProcessVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.OutSchoolVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.QueryHistoryListVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.QueryLeaveDetailVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave.QueryLeaveMessagesNumberVO;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leavedetail.LeaveDetailVO;
import chnytrcy.xyz.campusepidemicsystem.service.app.LeaveAppService;
import chnytrcy.xyz.campusepidemicsystem.utils.dozer.DozerUtils;
import chnytrcy.xyz.campusepidemicsystem.utils.md5.MD5;
import chnytrcy.xyz.campusepidemicsystem.utils.result.Result;
import chnytrcy.xyz.campusepidemicsystem.utils.result.ResultFactory;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.service.app.impl
 * @ClassName: LeaveAppServiceImpl
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/1 2:07 PM
 * @Version: 1.0
 */
@Service
public class LeaveAppServiceImpl extends ServiceImpl<LeaveMapper, Leave> implements LeaveAppService {

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  @Value("${school.county.code}")
  private String schoolAddressCode;

  @Value("${leave.restricted.speech}")
  private boolean speak;

  @Value("${minio.buckets.name}")
  private String bucket;

  @Value("${minio.buckets.isCreate}")
  private Boolean isCreate;

  @Autowired private MinioClient minioClient;

  @Autowired private StudentMapper studentMapper;

  @Autowired private AdminMapper adminMapper;

  @Autowired private LeaveDetailMapper leaveDetailMapper;

  @Autowired private IsolationPersonMapper isolationPersonMapper;

  @Autowired private LeaveHealthMapper leaveHealthMapper;

  @Autowired private StreetMapper streetMapper;
  
  @Autowired private UserCommon userCommon;

  @Autowired private StudentCommon studentCommon;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> addNewLeaveApplication(AddNewLeaveApplicationCommand command)
      throws ParseException {
    Long studentCode = Long.parseLong(userCommon.getAccount());
    //获得学生信息
    Student student = studentMapper.selectOne(
        new LambdaQueryWrapper<Student>().eq(Student::getCode, studentCode));
    //获得管理员编号
    Long adminId  = adminMapper.selectOne(
        new LambdaQueryWrapper<Admin>().eq(Admin::getDeptCode, student.getDeptCode())).getUserId();
    Long aLong = checkAddNewLeave(command, student);
    Leave convert = DozerUtils.convert(command, Leave.class);
    convert.setCode(student.getCode());
    convert.setName(student.getName());
    convert.setReason(command.getMessage());
    //1、判断是否跨天
    boolean sameDay = DateUtils.isSameDay(
        dateFormat.parse(command.getEstimateEndTime().toString()),
        dateFormat.parse(command.getEstimateStartTime().toString())
    );
    convert.setIsStrideDay(
        sameDay ? LeaveEnums.IS_STRIDE_DAY_NO.getCode() : LeaveEnums.IS_STRIDE_DAY_YES.getCode()
    );
    //2、判断是否跨区
    boolean sameCounty = command.getTarget().startsWith(schoolAddressCode);
    convert.setIsStrideCounty(
        sameCounty ? LeaveEnums.IS_STRIDE_COUNTY_NO.getCode() :
            LeaveEnums.IS_STRIDE_COUNTY_YES.getCode()
    );
    convert.setAdminId(adminId);
    convert.setIsReturn(LeaveEnums.IS_RETURN_NO.getCode());
    //3、如果同天并且在同个区,信誉为良好及以上，则免批，直接通过
    if(sameDay && sameCounty && student.getReputation() >= StudentEnums.REPUTATION_GOOD.getCode()){
      convert.setApprovalResult(LeaveEnums.APPROVAL_RESULT_AGREE.getCode());
      convert.setIsEnd(LeaveEnums.IS_END_YES.getCode());
      getBaseMapper().insert(convert);
      Long id = convert.getId();
      LeaveDetail leaveDetail = new LeaveDetail(
          id,
          LeaveDetailEnums.TYPE_END.getCode(),
          StringUtils.isNotBlank(command.getMessage()) ? command.getMessage() : null
          );
      leaveDetailMapper.insert(leaveDetail);
    }else {
      convert.setApprovalResult(LeaveEnums.APPROVAL_RESULT_NOT_PROCESSED.getCode());
      convert.setIsEnd(LeaveEnums.IS_END_NO.getCode());
      getBaseMapper().insert(convert);
      Long id = convert.getId();
      LeaveDetail leaveDetail = new LeaveDetail(
          id,
          LeaveDetailEnums.TYPE_STUDENT.getCode(),
          StringUtils.isNotBlank(command.getMessage()) ? command.getMessage() : null
      );
      leaveDetailMapper.insert(leaveDetail);
    }

    if(ObjectUtil.isNotNull(aLong)){
      delUnTimeLeave(aLong);
    }
    return ResultFactory.successResult();
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<OutSchoolVO> outSchool() {
    String studentCode = studentCommon.getStudentCode();
    List<Leave> leaves = getBaseMapper().selectList(
        new LambdaQueryWrapper<Leave>()
            .eq(Leave::getCode, studentCode)
            .orderByDesc(Leave::getCreateTime));
    this.checkLeaveListLegitimate(leaves,true);
    Leave leave = leaves.get(0);
    leave.setActualStartTime(LocalDateTime.now());
    leave.setIsStart(LeaveEnums.IS_START_YES.getCode());
    getBaseMapper().updateById(leave);
    OutSchoolVO convert = DozerUtils.convert(leave, OutSchoolVO.class);
    convert.setIsAllow(Boolean.TRUE);
    return ResultFactory.successResult(convert);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public Result<Void> backSchool(String nucleicAcid,MultipartFile file)
      throws IOException, ServerException, InsufficientDataException, ErrorResponseException,
      NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException,
      InternalException {
    if(ObjectUtil.isNull(nucleicAcid) ||
        (!nucleicAcid.equals("0") && !nucleicAcid.equals("1"))){
      throw new BusinessException(BusinessError.NUCLEIC_ACID_ERROR);
    }
    if(ObjectUtil.isNull(file) || file.getSize() <= 0L){
      throw new BusinessException(BusinessError.LEAVE_HEALTH_IMG_EMPTY_ERROR);
    }
    if(Integer.parseInt(nucleicAcid) == LeaveHealthEnums.NUCLEIC_ACID_POSITIVE.getCode()){
      throw new BusinessException(BusinessError.LEAVE_NUCLEIC_ACID_POSITIVE_ERROR);
    }
    //检查桶是否存在
    if(ObjectUtil.isNull(bucket)){
      throw new BusinessException(BusinessError.MINIO_BUCKET_INIT_ERROR);
    }
    if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())){
      if(isCreate.equals(Boolean.TRUE)){
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
      }else {
        throw new BusinessException(BusinessError.MINIO_BUCKET_NOT_CREATE_ERROR);
      }
    }
    String fileName = MD5.SysMd5(file.getOriginalFilename(),file.getOriginalFilename());
    String studentCode = studentCommon.getStudentCode();
    List<Leave> leaves = getBaseMapper().selectList(
        new LambdaQueryWrapper<Leave>()
            .eq(Leave::getCode, studentCode)
            .orderByDesc(Leave::getCreateTime));
    this.checkLeaveListLegitimate(leaves,false);
    Leave leave = leaves.get(0);
    leave.setActualEndTime(LocalDateTime.now());
    leave.setIsReturn(LeaveEnums.IS_RETURN_YES.getCode());
    getBaseMapper().updateById(leave);
    LeaveHealth leaveHealth = new LeaveHealth(
        leave.getId(),
        bucket + "_" +fileName,
        Integer.parseInt(nucleicAcid)
    );
    leaveHealthMapper.insert(leaveHealth);
    PutObjectArgs objectArgs = PutObjectArgs
        .builder()
        .object(fileName)
        .bucket(bucket)
        .contentType(file.getContentType())
        .stream(file.getInputStream(), file.getSize(), -1)
        .build();
    minioClient.putObject(objectArgs);
    return ResultFactory.successResult();
  }

  @Override
  public Result<BasePageVO<QueryHistoryListVO>> queryHistoryList(QueryHistoryListCommand command) {
    String studentCode = studentCommon.getStudentCode();
    PageHelper.startPage(command.getPageNum(),command.getPageSize());
    List<Leave> leaves = getBaseMapper().selectList(
        new LambdaQueryWrapper<Leave>()
            .eq(Leave::getCode, studentCode)
            .orderByDesc(Leave::getUpdateTime));
    PageInfo pageInfo = new PageInfo(leaves);
    List<QueryHistoryListVO> queryHistoryListVOS = DozerUtils.convertList(leaves,
        QueryHistoryListVO.class);
    pageInfo.setList(queryHistoryListVOS);
    return ResultFactory.successResult(new BasePageVO<>(pageInfo));
  }

  @Override
  public Result<QueryLeaveDetailVO> queryLeaveDetail(QueryLeaveDetailCommand command) {
    Leave leave = getBaseMapper().selectOne(
        new LambdaQueryWrapper<Leave>()
            .eq(Leave::getId, command.getId()));
    if(ObjectUtil.isNull(leave)){
      throw new BusinessException(BusinessError.LEAVE_IS_NOT_EXIST_ERROR);
    }
    if(!leave.getCode().equals(studentCommon.getStudentCode())){
      throw new BusinessException(BusinessError.LEAVE_NOT_BELONG_USER_ERROR);
    }
    String target = ObjectUtil.isNull(leave.getTarget()) ? "" : leave.getTarget();
    Street street = streetMapper.selectOne(
        new LambdaQueryWrapper<Street>()
            .eq(Street::getCode,target));
    if(ObjectUtil.isNull(street)){
      throw new BusinessException(BusinessError.STREET_IS_NOT_EXIST_ERROR);
    }
    Student student = studentMapper.selectOne(
        new LambdaQueryWrapper<Student>()
            .eq(Student::getCode, leave.getCode()));
    if(ObjectUtil.isNull(student)){
      throw new BusinessException(BusinessError.STUDENT_IS_NOT_EXIST_ERROR);
    }
    QueryLeaveDetailVO convert = DozerUtils.convert(leave, QueryLeaveDetailVO.class);
    convert.setTarget(street.getFullName());
    convert.setAdminName(student.getDeptName() + "管理员");
    List<LeaveDetail> leaveDetails = leaveDetailMapper.selectList(
        new LambdaQueryWrapper<LeaveDetail>()
            .eq(LeaveDetail::getRelId, convert.getId()));
    List<LeaveDetailProcessVO> leaveDetailVOS = DozerUtils.convertList(leaveDetails, LeaveDetailProcessVO.class);
    convert.setProcess(leaveDetailVOS);
    return ResultFactory.successResult(convert);
  }

  @Override
  public Result<QueryLeaveMessagesNumberVO> queryLeaveMessagesNumber() {
    int unReadNum = 0;
    String studentCode = studentCommon.getStudentCode();
    List<Leave> leaves = getBaseMapper().selectList(
        new LambdaQueryWrapper<Leave>()
            .eq(Leave::getCode, studentCode)
            .orderByDesc(Leave::getUpdateTime));
    int allNum = leaves.size();
    List<Long> collect = leaves
        .stream()
        .filter(e -> e.getApprovalResult().equals(LeaveEnums.APPROVAL_RESULT_DEAL.getCode()))
        .map(Leave::getId)
        .collect(Collectors.toList());
    if(CollUtil.isNotEmpty(collect)){
      List<LeaveDetail> leaveDetails = leaveDetailMapper.selectList(
          new LambdaQueryWrapper<LeaveDetail>()
              .eq(LeaveDetail::getRelId, collect.get(0))
              .orderByDesc(LeaveDetail::getCreateTime));
      if(leaveDetails.get(0).getType().equals(LeaveDetailEnums.TYPE_ADMIN_REPEAT.getNumber())){
        unReadNum++;
      }
    }
    QueryLeaveMessagesNumberVO key = new QueryLeaveMessagesNumberVO(unReadNum,allNum);
    return ResultFactory.successResult(key);
  }

  public Long checkAddNewLeave(AddNewLeaveApplicationCommand command,Student student){
    //检查是否有未完成的请假
    Leave unfinishedLeave = getBaseMapper().queryUnfinishedLeave(student.getCode());
    boolean overdue = false;
    if(!Objects.isNull(unfinishedLeave)){
      if(LeaveEnums.APPROVAL_RESULT_NOT_PROCESSED.getCode().equals(unfinishedLeave.getApprovalResult()) ||
          LeaveEnums.APPROVAL_RESULT_DEAL.getCode().equals(unfinishedLeave.getApprovalResult())){
        throw new BusinessException(BusinessError.LEAVE_ADMIN_IS_NOT_DEAL_ERROR);
      }
      if(!(LeaveEnums.IS_START_NO.getNumber().equals(unfinishedLeave.getIsStart()) &&
      unfinishedLeave.getEstimateEndTime().isBefore(LocalDateTime.now()))){
        if(LeaveEnums.IS_RETURN_NO.getNumber().equals(unfinishedLeave.getIsReturn())){
          throw new BusinessException(BusinessError.LEAVE_EXIST_NOT_RETURN_SCHOOL_ERROR);
        }
      }else {
        overdue = true;
      }
    }
    //判断返校时间是否早于离校时间
    if(command.getEstimateStartTime().isAfter(command.getEstimateEndTime())){
      throw new BusinessException(BusinessError.LEAVE_TIME_START_AFTER_END_ERROR);
    }
    //判断返校时间是否晚于当前时间
    if(command.getEstimateEndTime().isBefore(LocalDateTime.now())){
      throw new BusinessException(BusinessError.LEAVE_END_TIME_BEFORE_NOW_ERROR);
    }
    //判断该学生的合法性（不能为隔离人员）
    IsolationPerson isolationPerson = isolationPersonMapper.selectOne(
        new LambdaQueryWrapper<IsolationPerson>()
            .eq(IsolationPerson::getCode, student.getCode())
            .ne(IsolationPerson::getState, IsolationPersonEnums.STATE_END.getCode()));
    if(!Objects.isNull(isolationPerson)){
      throw new BusinessException(BusinessError.ISOLATION_PERSON_NOT_SUBMIT_LEAVE_ERROR);
    }
    if(overdue){
      return unfinishedLeave.getId();
    }else {
      return null;
    }
  }

  /**
   * 标记过期的请假记录
   * @param id
   */
  public void delUnTimeLeave(Long id){
    Leave leave = getBaseMapper().selectOne(
        new LambdaQueryWrapper<Leave>()
            .eq(Leave::getId, id));
    leave.setIsOverdue(LeaveEnums.IS_OVERDUE_YES.getCode());
    getBaseMapper().updateById(leave);
  }

  private void checkLeaveListLegitimate(List<Leave> data,Boolean isOut){
    //判断请假是否为空
    if(ObjectUtil.isNull(data) || data.isEmpty()){
      throw new BusinessException(BusinessError.LEAVE_IS_NOT_EXIST_ERROR);
    }
    Leave leave = data.get(0);
    //请假是否已审批结束
    if(leave.getIsEnd().equals(LeaveEnums.IS_END_NO.getCode())){
      throw new BusinessException(BusinessError.LEAVE_IS_DETAIL_ERROR);
    }
    //请假是否同意
    if(leave.getApprovalResult().equals(LeaveEnums.APPROVAL_RESULT_REJECT.getCode())){
      throw new BusinessException(BusinessError.LEAVE_REJECT_ERROR);
    }
    if(isOut){
      //当前时间是否早于开始时间
      if(leave.getEstimateStartTime().isAfter(LocalDateTime.now())){
        throw new BusinessException(BusinessError.LEAVE_TIME_AFTER_NOW_ERROR);
      }
      //当前时间是否晚于结束时间
      if(leave.getEstimateEndTime().isBefore(LocalDateTime.now())){
        throw new BusinessException(BusinessError.LEAVE_TIME_BEFORE_NOW_ERROR);
      }
      //判断是否已出校
      if(leave.getIsStart().equals(LeaveEnums.IS_START_YES.getCode())){
        throw new BusinessException(BusinessError.LEAVE_OUT_AGAIN_ERROR);
      }
    }else {
      if(leave.getIsStart().equals(LeaveEnums.IS_START_NO.getCode())){
        throw new BusinessException(BusinessError.LEAVE_NOT_START_LEAVE_ERROR);
      }
      if(leave.getIsReturn().equals(LeaveEnums.IS_RETURN_YES.getCode())){
        throw new BusinessException(BusinessError.LEAVE_RETURN_AGAIN_ERROR);
      }
    }

  }
}
