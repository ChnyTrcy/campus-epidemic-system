package xyz.chnytrcy.campusepidemicsystem.model.vo.app.feedbackacceptance;

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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.app.feedbackacceptance
 * @ClassName: QueryPageFeedbackAcceptanceByIsolationVO
 * @Author: ChnyTrcy
 * @Description: 隔离人员分页查询反馈受理VO
 * @Date: 2022/10/18 11:09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("隔离人员分页查询反馈受理VO")
public class QueryPageFeedbackAcceptanceByIsolationVO extends BaseId {

  @ApiModelProperty("反馈类型(1:物资请求，2:意见申诉)")
  private Integer type;

  @ApiModelProperty("反馈内容")
  private String message;

  @ApiModelProperty("创建时间")
  private LocalDateTime createTime;

  @ApiModelProperty("处理结果")
  private Integer result;

}
