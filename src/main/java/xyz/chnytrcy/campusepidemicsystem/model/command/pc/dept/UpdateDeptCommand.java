package xyz.chnytrcy.campusepidemicsystem.model.command.pc.dept;

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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.pc.dept
 * @ClassName: UpdateDeptCommand
 * @Author: ChnyTrcy
 * @Description: 更新院系信息Command
 * @Date: 2022/12/15 16:09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("更新院系名称Command")
public class UpdateDeptCommand extends BaseId {

  @ApiModelProperty("院系名称")
  @NotBlank(message = "院系名称不能为空")
  private String name;

}
