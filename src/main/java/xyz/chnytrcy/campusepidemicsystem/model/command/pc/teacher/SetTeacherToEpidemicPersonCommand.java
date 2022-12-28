package xyz.chnytrcy.campusepidemicsystem.model.command.pc.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.teacher
 * @ClassName: SetTeacherToEpidemicPersonCommand
 * @Author: ChnyTrcy
 * @Description: 设置教职工为防疫人员Command
 * @Date: 2022/8/28 4:03 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("设置教职工为防疫人员Command")
public class SetTeacherToEpidemicPersonCommand extends Base {

  @NotBlank(message = "教职工编号不能为空")
  @ApiModelProperty("工号")
  @Size(max = 4,min = 4,message = "工号长度错误")
  private String code;

}
