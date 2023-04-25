package xyz.chnytrcy.campusepidemicsystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;
import xyz.chnytrcy.core.config.basic.entity.EnumTransformation;
import xyz.chnytrcy.core.config.basic.entity.EnumValue;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.entity
 * @ClassName: Leave
 * @Author: ChnyTrcy
 * @Description: 日常出入校请假表
 * @Date: 2022/8/24 4:39 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("leave_daily")
@ApiModel("日常出入校请假表")
@EnumTransformation
public class Leave extends BaseEntity {

  @ApiModelProperty(value = "学生学号",required = true)
  private String code;

  @ApiModelProperty(value = "学生姓名",required = true)
  private String name;

  @ApiModelProperty(value = "请假类型（0:事假，1:病假）",required = true)
  @EnumValue("TYPE")
  private Integer type;

  @ApiModelProperty(value = "预计出校时间",required = true)
  private LocalDateTime estimateStartTime;

  @ApiModelProperty(value = "预计返校时间",required = true)
  private LocalDateTime estimateEndTime;

  @ApiModelProperty(value = "实际出校时间")
  private LocalDateTime actualStartTime;

  @ApiModelProperty(value = "实际返校时间")
  private LocalDateTime actualEndTime;

  @ApiModelProperty(value = "是否跨区",required = true)
  @TableField("is_stride_county")
  @EnumValue("IS_STRIDE_COUNTY")
  private Integer isStrideCounty;

  @ApiModelProperty(value = "是否跨天",required = true)
  @TableField("is_stride_day")
  @EnumValue("IS_STRIDE_DAY")
  private Integer isStrideDay;

  @ApiModelProperty(value = "是否已出校",required = true,example = "0")
  @EnumValue("IS_START")
  private Integer isStart;

  @ApiModelProperty(value = "是否已返校",required = true,example = "0")
  @EnumValue("IS_RETURN")
  private Integer isReturn;

  @ApiModelProperty(value = "审批是否结束",required = true,example = "0")
  @EnumValue("IS_END")
  private Integer isEnd;

  @ApiModelProperty(value = "是否过期",required = true,example = "0")
  @EnumValue("IS_OVERDUE_YES")
  private Integer isOverdue;

  @ApiModelProperty(value = "目的地（只能选择一个）",required = true)
  private String target;

  @ApiModelProperty(value = "审批结果（0:未处理，1:同意，2:拒绝，3：处理中）",required = true,example = "0")
  @EnumValue("APPROVAL_RESULT")
  private Integer approvalResult;

  @ApiModelProperty(value = "管理员Id",required = true)
  private Long adminId;

  @ApiModelProperty(value = "请假理由",required = true)
  private String reason;

  @TableField(exist = false)
  private Integer caseType = -1;
}
