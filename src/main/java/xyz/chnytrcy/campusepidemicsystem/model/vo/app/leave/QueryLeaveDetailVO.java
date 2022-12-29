package xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave;

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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave
 * @ClassName: QueryLeaveDetailVO
 * @Author: ChnyTrcy
 * @Description: 查询请假记录详情VO
 * @Date: 2022/10/7 15:52
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("出校VO")
public class QueryLeaveDetailVO extends BaseId {

  @ApiModelProperty(value = "学生学号")
  private String code;

  @ApiModelProperty(value = "学生姓名")
  private String name;

  @ApiModelProperty(value = "管理员名称")
  private String adminName;

  @ApiModelProperty(value = "请假类型（0:事假，1:病假）")
  private Integer type;

  @ApiModelProperty(value = "预计出校时间")
  private LocalDateTime estimateStartTime;

  @ApiModelProperty(value = "预计返校时间")
  private LocalDateTime estimateEndTime;

  @ApiModelProperty(value = "目的地（只能选择一个）")
  private String target;

  @ApiModelProperty(value = "审批结果（0:未处理，1:同意，2:拒绝，3：处理中）")
  private Integer approvalResult;

  @ApiModelProperty(value = "请假理由")
  private String reason;

  @ApiModelProperty(value = "请假流程")
  private List<LeaveDetailProcessVO> process;

}
