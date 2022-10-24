package chnytrcy.xyz.campusepidemicsystem.model.enums;

import lombok.AllArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.enums
 * @EnumName: EnumsEntityType
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/10/19 17:57
 * @Version: 1.0
 */
@AllArgsConstructor
public enum EnumsEntityType {
  BUSINESS_ERROR("BUSINESS_ERROR","事务异常","campus-epidemic-system","ces-101"),
  AUTHENTICATION_ERROR("AUTHENTICATION_ERROR","用户异常","campus-epidemic-system","ces-102"),
  SUCCESS_RETURN("SUCCESS_RETURN","成功返回","campus-epidemic-system","ces-103"),
  FEEDBACK_ACCEPTANCE("FEEDBACK_ACCEPTANCE","反馈受理","campus-epidemic-system","ces-1001"),
  ISOLATION_PERSON("ISOLATION_PERSON","隔离人员","campus-epidemic-system","ces-1002"),
  LEAVE_DETAIL("LEAVE_DETAIL","离校申请","campus-epidemic-system","ces-1003"),
  LEAVE("LEAVE","日常出入校离校","campus-epidemic-system","ces-1004"),
  LEAVE_HEALTH("LEAVE_HEALTH","请假健康","campus-epidemic-system","ces-1005"),
  ROLE("ROLE","角色","campus-epidemic-system","ces-1006"),
  STREET("STREET","街道","campus-epidemic-system","ces-1007"),
  STUDENT_DAILY("STUDENT_DAILY","学生日常打卡","campus-epidemic-system","ces-1008"),
  STUDENT("STUDENT","学生","campus-epidemic-system","ces-1009"),
  TEACHER_DAILY("TEACHER_DAILY","教职工日常打卡","campus-epidemic-system","ces-1010"),
  TEACHER("TEACHER","教职工","campus-epidemic-system","ces-1011")

  ;
  private String name;
  private String describe;
  private String app;
  private String appKey;



}
