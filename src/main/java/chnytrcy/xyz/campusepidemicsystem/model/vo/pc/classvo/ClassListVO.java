package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.classvo;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.classvo
 * @ClassName: ClassListVO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/25 3:01 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("班级列表VO")
public class ClassListVO extends Base {

  @ApiModelProperty("班级编号")
  private String code;

  @ApiModelProperty("班级名称")
  private String label;
}
