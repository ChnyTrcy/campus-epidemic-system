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
 * @ClassName: QueryPageIsolationPersonCommand
 * @Author: ChnyTrcy
 * @Description: 防疫人员分页查询本院系已隔离人员Command
 * @Date: 2022/8/31 3:46 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员分页查询本院系已隔离人员Command")
public class QueryPageIsolationPersonCommand extends BasePageCommand {

  @ApiModelProperty(value = "搜索类型（1：姓名，2：学号，3：班级名，4：专业名）",required = true)
  @NotNull(message = "搜索类型不能为空")
  @ListValue(vals = {1,2,3,4},message = "搜索类型错误")
  private Integer wordType;

  @ApiModelProperty("关键词")
  private String keyword;

}
