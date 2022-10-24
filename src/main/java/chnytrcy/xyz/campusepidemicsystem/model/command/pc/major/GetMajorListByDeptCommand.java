package chnytrcy.xyz.campusepidemicsystem.model.command.pc.major;

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
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.major
 * @ClassName: GetMajorListByDeptCommand
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/25 2:26 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("院系编号")
public class GetMajorListByDeptCommand extends Base {

  @NotBlank(message = "院系编号不能为空")
  @Size(max = 2,min = 2,message = "院系编号错误")
  private String deptCode;

}
