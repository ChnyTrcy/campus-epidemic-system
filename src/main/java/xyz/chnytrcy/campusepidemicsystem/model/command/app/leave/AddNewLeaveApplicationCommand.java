package xyz.chnytrcy.campusepidemicsystem.model.command.app.leave;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.leave
 * @ClassName: AddNewLeaveApplicationCommand
 * @Author: ChnyTrcy
 * @Description: 添加新的请假记录Command
 * @Date: 2022/9/1 2:09 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("添加新的请假记录Command")
public class AddNewLeaveApplicationCommand extends Base {

  @ApiModelProperty("请假原因")
  @NotBlank(message = "不能为空")
  private String message;

  @ApiModelProperty(value = "请假类型（0:事假，1:病假）",required = true)
  @NotNull(message = "请假类型不能为空")
  @ListValue(vals = {0,1},message = "请假类型错误")
  private Integer type;

  @ApiModelProperty(value = "预计出校时间",required = true)
  @NotNull(message = "预计出校时间不能为空")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime estimateStartTime;

  @ApiModelProperty(value = "预计返校时间",required = true)
  @NotNull(message = "预计返校时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime estimateEndTime;

  @ApiModelProperty(value = "目的地（只能选择一个）",required = true)
  @NotBlank(message = "目的地不能为空")
  @Size(max = 9,min = 9,message = "目的地街道编号长度错误")
  private String target;

}
