package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import chnytrcy.xyz.campusepidemicsystem.model.dto.StatisticalNameCountDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis
 * @ClassName: CountDeptPeopleProportionVO
 * @Author: ChnyTrcy
 * @Description: 各学院防疫人员和隔离人员配比VO
 * @Date: 2022/10/20 15:59
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("各学院防疫人员和隔离人员配比VO")
public class CountDeptPeopleProportionVO extends Base {

  @ApiModelProperty("防疫人员列表")
  private List<StatisticalNameCountDTO> epidemicList;

  @ApiModelProperty("隔离人员列表")
  private List<StatisticalNameCountDTO> isolatedList;
}
