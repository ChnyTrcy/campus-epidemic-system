package chnytrcy.xyz.campusepidemicsystem.model.dto;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.dto
 * @ClassName: HolidayStreetRiskLevelDTO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/29 3:42 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HolidayStreetRiskLevelDTO extends Base {

  @ApiModelProperty("学生姓名")
  private String name;

  @ApiModelProperty("学生学号")
  private String code;

  @ApiModelProperty("联系方式")
  private String phone;
}
