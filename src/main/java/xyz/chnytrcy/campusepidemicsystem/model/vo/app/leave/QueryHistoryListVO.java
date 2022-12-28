package xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave
 * @ClassName: QueryHistoryListVO
 * @Author: ChnyTrcy
 * @Description: 查询自己的历史请假记录VO
 * @Date: 2022/10/7 15:36
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询自己的历史请假记录VO")
public class QueryHistoryListVO extends BaseId {

  @ApiModelProperty(value = "学生姓名")
  private String name;

  @ApiModelProperty(value = "审批时间")
  private LocalDateTime updateTime;

  @ApiModelProperty(value = "审批结果（0:未处理，1:同意，2:拒绝，3：处理中）",example = "0")
  private Integer approvalResult;

}
