package xyz.chnytrcy.campusepidemicsystem.model.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.dto
 * @ClassName: AddMessageInfoDTO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/29 5:31 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("添加到信息队列体")
public class AddMessageInfoDTO extends Base {

  private String name;

  private String streetName;

  private Integer riskLevel;

  private String quarantineLocation = "校园集中隔离点A";

  private String phone;

}
