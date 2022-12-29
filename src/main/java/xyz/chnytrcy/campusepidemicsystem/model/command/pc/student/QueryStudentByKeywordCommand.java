package xyz.chnytrcy.campusepidemicsystem.model.command.pc.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.basic.model.BasePageCommand;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.student
 * @ClassName: QueryStudentByKeywordCommand
 * @Author: ChnyTrcy
 * @Description: 关键词查询学生Command
 * @Date: 2022/8/25 9:18 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("关键词分页查询学生Command")
public class QueryStudentByKeywordCommand extends BasePageCommand {

  @ApiModelProperty("关键字（智能匹配：学号，名字，班级名，专业名，院系名）")
  private String keyword;

  @ApiModelProperty("类型（学号：0，姓名：1，班级名：2，专业名：3，院系名：4）")
  @ListValue(vals = {0,1,2,3,4},message = "搜索类型")
  private Integer wordType;

}
