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
 * @ClassName: DeleteTeacherCommand
 * @Author: ChnyTrcy
 * @Description: 删除教职工Command
 * @Date: 2022/8/28 2:51 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("删除教职工Command")
@EqualsAndHashCode(callSuper = true)
public class DeleteTeacherCommand extends Base {

  @ApiModelProperty(value = "编号",required = true)
  @NotBlank(message = "教职工编号不能为空")
  @Size(max = 4,min = 4,message = "教职工编号非法")
  private String code;

}
