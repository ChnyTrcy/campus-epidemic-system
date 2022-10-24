package chnytrcy.xyz.campusepidemicsystem.model.command.app.studentdaily;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.studentdaily
 * @ClassName: AddStudentDailyCommand
 * @Author: ChnyTrcy
 * @Description: 添加学生日常打卡Command
 * @Date: 2022/10/7 10:39
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("添加学生日常打卡Command")
public class AddStudentDailyCommand extends Base {

  @ApiModelProperty(value = "体温是否异常",required = true)
  @ListValue(vals = {0,1},message = "体温异常字段数据错误")
  @NotNull(message = "体温异常字段不能为空")
  private Integer temperatureAbnormal;

  @ApiModelProperty(value = "是否有异常症状",required = true)
  @ListValue(vals = {0,1},message = "异常症状字段数据错误")
  @NotNull(message = "异常症状字段不能为空")
  private Integer abnormalSymptoms;

  @ApiModelProperty(value = "健康码颜色（0:绿，1:黄，2:红）",required = true)
  @ListValue(vals = {0,1,2},message = "健康码字段数据错误")
  @NotNull(message = "健康码字段不能为空")
  private Integer healthCode;

  @ApiModelProperty(value = "承诺（0:接受，1:拒绝）",required = true)
  @ListValue(vals = {0,1},message = "承诺字段数据错误")
  @NotNull(message = "承诺字段不能为空")
  private Integer promise;

  @ApiModelProperty(value = "精确地区",required = true)
  @NotBlank(message = "精确地区不能为空")
  @Size(max = 200,message = "精确地区数据过长")
  private String preciseArea;
}
