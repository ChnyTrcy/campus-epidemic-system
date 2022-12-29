package xyz.chnytrcy.campusepidemicsystem.model.command.pc.studentdalily;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.pc.student
 * @ClassName: QueryStudentDailyCommand
 * @Author: ChnyTrcy
 * @Description: 查询学生日常打卡记录列表Command
 * @Date: 2022/10/7 13:31
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查询学生日常打卡记录列表Command")
public class QueryStudentDailyCommand extends BaseId {

  @ApiModelProperty(value = "月份",required = true)
  @NotNull(message = "月份不能为空")
  @ListValue(vals = {1,2,3,4,5,6,7,8,9,10,11,12},message = "月份错误")
  private Integer month;

  @ApiModelProperty(value = "年份",required = true)
  @NotNull(message = "年份不能为空")
  private Integer year;

}
