package chnytrcy.xyz.campusepidemicsystem.model.enums;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.EnumKey;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseEnum;
import cn.hutool.core.util.PinyinUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * @Description: 错误类型枚举
 * @Author: ChnyTrcy
 * @Date: 2022/8/20 4:13 PM
 */
@Getter
@AllArgsConstructor
@EnumKey(name = "错误返回参数",type = EnumsEntityType.BUSINESS_ERROR)
public enum BusinessError implements BaseEnum {
  VALID_DATA_ERROR(10000,null),
  USER_ADD_ERROR(10001,"用户添加失败"),
  DEPT_NOT_EXIST_ERROR(10002,"院系不存在"),
  MAJOR_NOT_EXIST_ERROR(10003,"专业不存在"),
  CLASS_NOT_EXIST_ERROR(10004,"班级不存在"),
  STUDENT_CODE_TO_DEPT_PARAMETER_ERROR(10005,"学生学号/班级/专业/院系相互匹配错误"),
  STUDENT_CODE_EXIST_ERROR(10006,"学号已被注册"),
  STUDENT_IN_UNDONE_FEEDBACK_ACCEPTANCE_ERROR(10007,"学生存在未结束的反馈受理记录"),
  STUDENT_IN_UNDONE_ISOLATION_PERSON_ERROR(10008,"学生还处于隔离状态"),
  STUDENT_IN_UNDONE_LEAVE_ERROR(10009,"学生存在未结束的请假"),
  TEACHER_IN_UNDONE_FEEDBACK_ACCEPTANCE_ERROR(10010,"教职工存在未结束的反馈受理表"),
  TEACHER_IN_UNDONE_ISOLATION_STUDENT_ERROR(10011,"教职工存在正在隔离的学生"),
  STREET_SAME_AS_ORIGINAL_ERROR(10012,"街道修改的风险等级与原来相同"),
  STREET_IS_NOT_EXIST_ERROR(10013,"街道不存在"),
  ADMIN_IS_NOT_EXIST_ERROR(10014,"管理员不存在"),
  FEEDBACK_ACCEPTANCE_IS_NOT_EXIST_ERROR(10015,"该反馈受理不存在"),
  FEEDBACK_ACCEPTANCE_END_ERROR(10016,"该反馈受理已经处理完毕"),
  FEEDBACK_ACCEPTANCE_ILLEGAL_DETAIL_ERROR(10017,"反馈受理进度还没到管理员处理，非法处理"),
  FEEDBACK_ACCEPTANCE_NOT_DETAIL_THEMSELF_ERROR(10018,"管理员不能处理不是自己管辖的反馈受理"),
  LEAVE_TIME_START_AFTER_END_ERROR(10019,"开始时间不能晚于结束时间"),
  LEAVE_IS_NOT_EXIST_ERROR(10020,"该请假记录不存在"),
  LEAVE_END_ERROR(10021,"请假记录已经处理过了"),
  LEAVE_NOT_DETAIL_THEMSELF_ERROR(10022,"管理员不能处理不是自己管辖的请假"),
  TEACHER_IS_NOT_EXIST_ERROR(10023,"教职工不存在"),
  ISOLATION_PERSON_IS_NOT_EXIST_ERROR(10024,"隔离人员不存在"),
  ISOLATION_PERSON_HAVING_UNDONE_ERROR(10025,"该隔离人员还有未完成的隔离记录"),
  ISOLATION_PERSON_STATE_NOT_EQUALS_ZERO_ERROR(10026,"该隔离人员状态未处于待隔离状态"),
  LEAVE_ADMIN_IS_NOT_DEAL_ERROR(10027,"管理员还在处理离校申请，请不要重复提交"),
  LEAVE_EXIST_NOT_RETURN_SCHOOL_ERROR(10028,"存在未返校的离校记录"),
  ISOLATION_PERSON_NOT_STATE_QUARANTINED_ERROR(10029,"隔离人员状态错误，非已隔离状态"),
  ISOLATION_DETAIL_COUNT_LESS_ERROR(10030,"隔离记录不够14天"),
  TEACHER_CODE_IS_EXIST_ERROR(10031,"教职工工号不能重复"),
  FEEDBACK_ACCEPTANCE_UNABLE_PROCESS_TRANSACTION_HAS_ALREADY_PROCESSED_ERROR(10032,"无法处理已经处理过的反馈受理"),
  FEEDBACK_ACCEPTANCE_RETURN_MESSAGE_LENGTH_LONG_ERROR(10033,"反馈受理回复信息过长"),
  TEACHER_IS_NOT_EPIDEMIC_PREVENTION_ERROR(10034,"该教职工不是防疫人员"),
  LEAVE_END_TIME_BEFORE_NOW_ERROR(10035,"请假返校时间不能早于当前时间"),
  STUDENT_IS_NOT_EXIST_ERROR(10036,"学生不存在"),
  STUDENT_DEAL_NOT_MYSELF_LEAVE_ERROR(10037,"学生不能处理不是自己的请假申请"),
  ISOLATION_PERSON_NOT_SUBMIT_LEAVE_ERROR(10038,"隔离人员不能提出离校申请"),
  LEAVE_DETAIL_NOT_FOUND_STUDENT_ERROR(10039,"管理员找不到对应回复的请假记录"),
  LEAVE_DETAIL_NOT_FOUNT_ADMIN_ERROR(10040,"学生找不到对应回复的请假记录"),
  LEAVE_RETURN_MESSAGE_IS_END_ERROR(10041,"不能回复已经审批结束的请假记录"),
  TEACHER_NOT_EPIDEMIC_ERROR(10042,"该教职工不是防疫人员"),
  EPIDEMIC_PREVENTION_QR_CODE_INVALID_ERROR(10043,"校园防疫码失效"),
  ISOLATION_PERSON_NOT_IN_EPIDEMIC_DEPT_ERROR(10044,"防疫人员不能处理非本院系的学生"),
  ISOLATION_PERSON_TEMPERATURE_NUMBER_ERROR(10045,"隔离人员体温次数小于14次"),
  LEAVE_IS_DETAIL_ERROR(10046,"请假记录还在处理，请管理员处理"),
  LEAVE_REJECT_ERROR(10047,"管理员拒绝出校"),
  LEAVE_TIME_AFTER_NOW_ERROR(10048,"还没有到达请假时间"),
  LEAVE_TIME_BEFORE_NOW_ERROR(10049,"当前时间晚于请假结束时间"),
  LEAVE_HEALTH_IMG_EMPTY_ERROR(10050,"健康码不能为空"),
  LEAVE_NUCLEIC_ACID_POSITIVE_ERROR(10051,"核酸阳性，请联系管理员处理"),
  MINIO_BUCKET_NOT_CREATE_ERROR(10052,"MINIO-创建桶失败"),
  MINIO_BUCKET_INIT_ERROR(10053,"MINIO-桶初始化失败"),
  LEAVE_NOT_START_LEAVE_ERROR(10054,"该学生还未出校，返校失败"),
  LEAVE_OUT_AGAIN_ERROR(10055,"已经出校，不能重复出校"),
  LEAVE_RETURN_AGAIN_ERROR(10056,"已经返校，不能重复返校"),
  NUCLEIC_ACID_ERROR(10057,"核酸入参错误"),
  STUDENT_DAILY_AGAIN_ERROR(10058,"学生不能重复日常打卡"),
  STUDENT_DAILY_PROMISE_NO_ERROR(10059,"学生日常打卡承诺不能为拒绝"),
  STUDENT_DAILY_YEAR_AFTER_NOW_ERROR(10060,"学生日常打卡查询参数时间不能晚于当前月份"),
  LEAVE_NOT_BELONG_USER_ERROR(10061,"该用户无权限查看此条请假记录详情"),
  TEACHER_DAILY_UP_AGAIN_ERROR(10062,"教职工不能重复上班打卡"),
  TEACHER_DAILY_DOWN_AGAIN_ERROR(10063,"教职工不能重复下班打卡"),
  FEEDBACK_ACCEPTANCE_UNTREATED_ERROR(10064,"有未处理的反馈受理"),
  RATE_LIMIT_BUSY_ERROR(10065,"请不要重复请求"),
  DEPT_NAME_EXIST_ERROR(10066,"院系名称重复"),
  ENTITY_ENUMS_NOT_EXIST_ERROR(10067,"未知的实体枚举类"),
  MAJOR_NAME_REPEAT_ERROR(10068,"专业名称重复")

  ;

  private Integer code;

  private String desc;

  @Override
  public Integer getNumber() {
    return getCode();
  }

  @Override
  public String getName() {
    return getDesc();
  }

  @Override
  public String getSimplePinYin() {
    return PinyinUtil.getAllFirstLetter(getDesc());
  }

  @Override
  public String getFullPinYin() {
    return PinyinUtil.getPinYin(getDesc());
  }

}
