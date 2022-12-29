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
 * @ClassName: QueryPageIsolationPersionCommand
 * @Author: ChnyTrcy
 * @Description: 分页查询隔离人员Command
 * @Date: 2022/8/30 9:42 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询隔离人员Command")
public class QueryPageAdminIsolationPersonCommand extends BasePageCommand {

  @ApiModelProperty("关键词")
  private String keyword;

  @ApiModelProperty(value = "查询类型(0～2：姓名、学号、班级)",required = true)
  @ListValue(vals = {0,1,2},message = "查询类型错误")
  @NotNull(message = "查询类型不能为空")
  private Integer wordType;

  @ApiModelProperty("隔离状态（-1：默认，0:通知未隔离,1:已隔离，2:治疗中，3:隔离结束）")
  @ListValue(vals = {-1,0,1,2,3},message = "隔离状态错误")
  @NotNull(message = "隔离状态不能为空")
  private Integer state;
}
