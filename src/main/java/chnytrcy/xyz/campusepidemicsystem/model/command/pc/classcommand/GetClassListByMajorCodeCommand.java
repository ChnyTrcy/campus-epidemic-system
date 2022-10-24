package chnytrcy.xyz.campusepidemicsystem.model.command.pc.classcommand;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.classcommand
 * @ClassName: GetClassListByMajorCodeCommand
 * @Author: ChnyTrcy
 * @Description: 根据专业编号获取班级列表Command
 * @Date: 2022/8/25 2:38 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("根据专业编号获取班级列表Command")
public class GetClassListByMajorCodeCommand extends Base {

  @NotBlank(message = "专业编号不能为空")
  @Size(min = 4,max = 4,message = "专业编号长度错误")
  private String majorCode;
}
