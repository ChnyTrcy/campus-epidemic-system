package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leave;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import chnytrcy.xyz.campusepidemicsystem.model.vo.pc.leavedetail.LeaveDetailVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.leave
 * @ClassName: QueryPageLeaveListVO
 * @Author: ChnyTrcy
 * @Description: 查看本院系的请假列表
 * @Date: 2022/8/30 4:29 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查看本院系的请假列表VO")
public class QueryPageLeaveListVO extends BaseId {

  @ApiModelProperty(value = "学生学号")
  private String code;

  @ApiModelProperty(value = "学生姓名")
  private String name;

  @ApiModelProperty(value = "请假类型（0:事假，1:病假）")
  private Integer type;

  @ApiModelProperty(value = "预计出校时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime estimateStartTime;

  @ApiModelProperty(value = "预计返校时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime estimateEndTime;

  @ApiModelProperty(value = "实际出校时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime actualStartTime;

  @ApiModelProperty(value = "实际返校时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime actualEndTime;

  @ApiModelProperty(value = "是否跨区")
  private Integer isStrideCounty;

  @ApiModelProperty(value = "是否跨天")
  private Integer isStrideDay;

  @ApiModelProperty(value = "是否已返校")
  private Integer isReturn;

  @ApiModelProperty(value = "目的地（只能选择一个）")
  private String target;

  @ApiModelProperty(value = "审批结果（0:未处理，1:同意，2:拒绝）")
  private Integer approvalResult;

  @ApiModelProperty(value = "请假原因")
  private String reason;

  @ApiModelProperty(value = "信誉等级")
  private String reputation;

  @ApiModelProperty(value = "与管理员交流的对话情况")
  private List<LeaveDetailVO> complex;

}
