package chnytrcy.xyz.campusepidemicsystem.model.command.pc.leave;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BasePageCommand;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.leave
 * @ClassName: QueryPageLeaveListCommand
 * @Author: ChnyTrcy
 * @Description: 查看本院系的请假列表Command
 * @Date: 2022/8/30 3:40 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查看本院系的请假列表Command")
public class QueryPageLeaveListCommand extends BasePageCommand {

  @ApiModelProperty(value = "类型过滤(0:默认，1：只查看未处理,2:只查看已处理)",required = true)
  @NotNull(message = "类型过滤不能为空")
  @ListValue(vals = {0,1,2},message = "类型过滤数据错误")
  private Integer typeFilter;

  @ApiModelProperty("过滤开始时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime startTime;

  @ApiModelProperty("过滤结束时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime endTime;

  @ApiModelProperty(value = "字段类型（1、学号,2、姓名，3、班级名）",required = true)
  @ListValue(vals = {1,2,3},message = "查找类型错误")
  @NotNull(message = "查找类型不能为空")
  private Integer wordType;

  @ApiModelProperty("关键字")
  private String keyword;

  @ApiModelProperty(value = "是否跨区(-1:默认，0：否，1：是)",required = true)
  @ListValue(vals = {-1,0,1},message = "跨区参数错误")
  @NotNull(message = "跨区参数不能为空")
  private Integer isStrideCounty;

  @ApiModelProperty(value = "是否跨天(-1:默认，0：否，1：是)",required = true)
  @ListValue(vals = {-1,0,1},message = "跨天参数错误")
  @NotNull(message = "跨天参数不能为空")
  private Integer isStrideDay;

  @ApiModelProperty(value = "是否已返校(-1:默认，0：否，1：是)",required = true)
  @ListValue(vals = {-1,0,1},message = "返校参数错误")
  @NotNull(message = "返校参数不能为空")
  private Integer isReturn;
}
