package chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationdetail;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.app.isolationdetail
 * @ClassName: AddTemperatureVO
 * @Author: ChnyTrcy
 * @Description: 添加体温VO
 * @Date: 2022/9/18 01:48
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("添加体温VO")
public class AddTemperatureVO extends Base {

  @ApiModelProperty("是否异常")
  private Boolean isAbnormal;
}
