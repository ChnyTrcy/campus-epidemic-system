package xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.app.leave
 * @ClassName: QueryLeaveMessagesNumberVO
 * @Author: ChnyTrcy
 * @Description: 该学生未读和所有请假数量VO
 * @Date: 2022/10/7 17:38
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("该学生未读和所有请假数量VO")
public class QueryLeaveMessagesNumberVO extends Base {

  @ApiModelProperty(value = "未读数量")
  private Integer unReadNum;

  @ApiModelProperty(value = "所有数量")
  private Integer allNum;

}
