package chnytrcy.xyz.campusepidemicsystem.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.enums
 * @EnumName: EntityEnums
 * @Author: ChnyTrcy
 * @Description: 实体类枚举
 * @Date: 2022/12/2 15:09
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum EntityEnums {
  ADMIN("admin","管理员"),
  CITY("city","城市"),
  CLASS_ENTITY("class","班级"),
  COUNTY("county","区县"),
  DEPT("dept","院系"),
  FEEDBACK_ACCEPTANCE("feedback_acceptance","反馈受理"),
  HOLIDAY_STREET("holiday_street","寒暑假返校"),
  ISOLATION_DETAIL("isolation_detail","隔离记录"),
  ISOLATION_PERSON("isolation_person","隔离人员"),
  LEAVE_DETAIL("leave_detail","离校申请过程"),
  LEAVE_DAILY("leave_daily","日常出入校申请"),
  LEAVE_HEALTH("leave_health","请假返校健康"),
  MAJOR("major","专业"),
  PROVINCE("province","省份"),
  STREET("street","街道"),
  STUDENT("student","学生"),
  STUDENT_DAILY("student_daily","学生日常打卡"),
  TEACHER("teacher","教职工"),
  TEACHER_DAILY("teacher_daily","教职工日常打卡")
  ;
  /**
   * 数据表名
   */
  private String tableName;

  /**
   * 数据表注释名
   */
  private String tableComment;
}
