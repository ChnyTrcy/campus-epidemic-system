package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.campusepidemicsystem.model.dto.StatisticalNameCountDTO;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis
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
