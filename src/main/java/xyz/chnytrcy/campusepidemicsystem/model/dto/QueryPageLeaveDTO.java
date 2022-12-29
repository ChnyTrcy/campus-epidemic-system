package xyz.chnytrcy.campusepidemicsystem.model.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.dto
 * @ClassName: QueryPageLeaveDTO
 * @Author: ChnyTrcy
 * @Description: 查看本院系的请假列表DTO
 * @Date: 2022/9/3 9:37 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查看本院系的请假列表DTO")
public class QueryPageLeaveDTO extends BaseId {

  @ApiModelProperty(value = "学生学号",required = true)
  private String code;

  @ApiModelProperty(value = "学生姓名",required = true)
  private String name;

  @ApiModelProperty(value = "请假类型（0:事假，1:病假）",required = true)
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
  private Integer isStrideCounty;

  @ApiModelProperty(value = "是否跨天",required = true)
  @TableField("is_stride_day")
  private Integer isStrideDay;

  @ApiModelProperty(value = "是否已返校",required = true,example = "0")
  @TableField("is_return")
  private Integer isReturn;

  @ApiModelProperty(value = "请假原因")
  private String reason;

  @ApiModelProperty(value = "目的地（只能选择一个）",required = true)
  private String target;

  @ApiModelProperty(value = "审批结果（0:未处理，1:同意，2:拒绝）",required = true,example = "0")
  private Integer approvalResult;

  @ApiModelProperty(value = "管理员Id",required = true)
  private Long adminId;

  @ApiModelProperty("信誉值")
  private Integer reputation;

  @ApiModelProperty("交流")
  private List<LeaveDetailDTO> complex;
}
