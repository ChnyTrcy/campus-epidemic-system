package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.major;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.major
 * @ClassName: MajorListVO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/25 2:15 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("专业列表VO")
public class MajorListVO extends Base {

  @ApiModelProperty("院系编号")
  private String code;

  @ApiModelProperty("院系名称")
  private String label;
}
