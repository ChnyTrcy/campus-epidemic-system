package xyz.chnytrcy.campusepidemicsystem.model.command.pc.isolationperson;

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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.isolationperson
 * @ClassName: QueryPageToBeIsolationPersonCommand
 * @Author: ChnyTrcy
 * @Description: 分页查询待隔离人员Command
 * @Date: 2022/8/30 5:16 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询待隔离人员Command")
public class QueryPageToBeIsolationPersonCommand extends BasePageCommand {

  @ApiModelProperty(value = "搜索类型（1：专业，2：班级）",required = true)
  @ListValue(vals = {1,2},message = "搜索类型错误")
  @NotNull(message = "搜索类型不能为空")
  private Integer wordType;

  @ApiModelProperty(value = "关键词")
  private String keyword;

}
