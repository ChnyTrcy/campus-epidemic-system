package xyz.chnytrcy.campusepidemicsystem.model.dto;

import xyz.chnytrcy.campusepidemicsystem.model.enums.EnumsEntityType;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.dto
 * @ClassName: DictEnumsEntity
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/8 19:09
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictEnumsEntityDTO {

  @ApiModelProperty("枚举类型")
  private EnumsEntityType enumsEntityType;

  @ApiModelProperty("枚举列表")
  private Set<DictEnumsValueDTO> valueDTOSet;

}
