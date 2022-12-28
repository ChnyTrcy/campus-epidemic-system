package xyz.chnytrcy.campusepidemicsystem.model.command.app.leave;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.leave
 * @ClassName: ReturnLeaveToAdminMessageCommand
 * @Author: ChnyTrcy
 * @Description: 学生回复管理员请假理由Command
 * @Date: 2022/9/7 11:13 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("学生回复管理员请假理由Command")
public class ReturnLeaveToAdminMessageCommand extends BaseId {

  @ApiModelProperty(value = "回复信息",required = true)
  @NotBlank(message = "回复信息不能为空")
  private String message;
}
