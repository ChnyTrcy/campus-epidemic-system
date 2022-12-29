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
 * @ClassName: QueryTeacherPageCommand
 * @Author: ChnyTrcy
 * @Description: 分页查询教职工Command
 * @Date: 2022/8/26 5:01 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询教职工Command")
public class QueryTeacherPageCommand extends BasePageCommand {

  @ApiModelProperty("关键词")
  private String keyword;

  @ApiModelProperty(value = "搜索类型（工号：0，姓名：1，所属院系：2）",required = true)
  @ListValue(vals = {0,1,2},message = "搜索类型错误")
  @NotNull(message = "搜索类型不能为空")
  private Integer wordType;
}
