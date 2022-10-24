package chnytrcy.xyz.campusepidemicsystem.config.basic.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.config.basic.model
 * @ClassName: BaseId
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/30 10:29 AM
 * @Version: 1.0
 */
@Data
public class BaseId extends Base{

  @ApiModelProperty("唯一编号")
  @NotNull(message = "id不能为空")
  @JsonSerialize(using= ToStringSerializer.class)
  private Long id;

}
