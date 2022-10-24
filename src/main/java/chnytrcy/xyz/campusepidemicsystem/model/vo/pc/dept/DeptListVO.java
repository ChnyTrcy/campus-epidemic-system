package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.dept;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.dept
 * @ClassName: DeptListVO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/25 1:45 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("返回院系列表")
public class DeptListVO extends Base {

  @ApiModelProperty("院系编号")
  private String code;

  @ApiModelProperty("院系名称")
  private String label;

}
