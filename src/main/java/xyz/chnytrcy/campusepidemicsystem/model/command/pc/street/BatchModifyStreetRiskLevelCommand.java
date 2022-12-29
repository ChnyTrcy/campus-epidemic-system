package xyz.chnytrcy.campusepidemicsystem.model.command.pc.street;

import io.swagger.annotations.ApiModel;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.street
 * @ClassName: BatchModifyStreetRiskLevelCommand
 * @Author: ChnyTrcy
 * @Description: 批量修改风险地区等级
 * @Date: 2022/8/29 5:59 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("批量修改风险地区等级")
public class BatchModifyStreetRiskLevelCommand extends Base {

  @Valid
  @NotEmpty(message = "list不能为空")
  private List<ModifyStreetRiskLevelCommand> list;

}
