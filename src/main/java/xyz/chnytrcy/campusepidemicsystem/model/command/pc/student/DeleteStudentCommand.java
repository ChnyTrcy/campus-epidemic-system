package xyz.chnytrcy.campusepidemicsystem.model.command.pc.student;

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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.student
 * @ClassName: DeleteStudentCommand
 * @Author: ChnyTrcy
 * @Description: 删除学生Command
 * @Date: 2022/8/25 3:07 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("删除学生Command")
public class DeleteStudentCommand extends Base {

  @ApiModelProperty(value = "学号",required = true)
  @NotBlank(message = "学号不能为空")
  @Size(min = 10,max = 10,message = "学生超出长度限制")
  private String code;
}
