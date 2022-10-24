package chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacherdaily;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.pc.teacherdaily
 * @ClassName: queryTeacherDailyHistoryCommand
 * @Author: ChnyTrcy
 * @Description: 查看10天该教职工的历史打卡记录Command
 * @Date: 2022/9/6 9:54 AM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("查看10天该教职工的历史打卡记录Command")
public class QueryTeacherDailyHistoryCommand extends Base {

  @ApiModelProperty(value = "工号",required = true)
  @NotBlank(message = "工号不能为空")
  @Size(max = 4,min = 4,message = "工号格式错误")
  private String code;

}
