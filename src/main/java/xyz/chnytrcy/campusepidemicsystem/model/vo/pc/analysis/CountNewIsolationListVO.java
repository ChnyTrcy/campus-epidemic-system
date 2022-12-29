package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.campusepidemicsystem.model.dto.StatisticalDateCountDTO;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.pc.analysis
 * @ClassName: CountNewIsolationListVO
 * @Author: ChnyTrcy
 * @Description: 新增隔离人数趋势VO
 * @Date: 2022/10/20 17:00
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("新增隔离人数趋势VO")
public class CountNewIsolationListVO extends Base {

  List<StatisticalDateCountDTO> data;

}
