package xyz.chnytrcy.campusepidemicsystem.model.command.pc.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.teacher
 * @ClassName: QueryEpidemicPersonCommand
 * @Author: ChnyTrcy
 * @Description: 查询本院系防疫人员Command
 * @Date: 2022/8/30 10:47 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询本院系防疫人员Command")
public class QueryEpidemicPersonCommand extends BasePageCommand {

  @ApiModelProperty("查询类型(1:姓名,2:工号)")
  @ListValue(vals = {1,2},message = "查询类型错误")
  @NotNull(message = "查询类型不能为空")
  private Integer wordType;

  @ApiModelProperty("搜索关键词")
  private String keyword;
}
