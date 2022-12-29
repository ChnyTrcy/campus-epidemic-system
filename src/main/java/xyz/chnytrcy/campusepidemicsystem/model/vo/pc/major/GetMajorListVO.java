package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.major;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.pc.major
 * @ClassName: GetMajorListVO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/16 16:08
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("获得专业列表VO")
public class GetMajorListVO extends BaseId {

  @ApiModelProperty("专业名")
  private String name;

  @ApiModelProperty("专业编号")
  private String code;

  @ApiModelProperty("院系名称")
  private String deptName;

}
