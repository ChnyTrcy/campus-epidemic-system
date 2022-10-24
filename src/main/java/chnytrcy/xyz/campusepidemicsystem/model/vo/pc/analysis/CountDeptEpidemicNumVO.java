package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import chnytrcy.xyz.campusepidemicsystem.model.dto.StatisticalNameCountDTO;
import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.pc.analysis
 * @ClassName: CountDeptEpidemicNumVO
 * @Author: ChnyTrcy
 * @Description: 各学院防疫人数占比VO
 * @Date: 2022/10/20 16:50
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("各学院防疫人数占比VO")
public class CountDeptEpidemicNumVO extends Base {

  private List<StatisticalNameCountDTO> data;

}
