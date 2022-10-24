package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.isolationdetail;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.isolationdetail
 * @ClassName: QueryIsolationDetailByIdVO
 * @Author: ChnyTrcy
 * @Description: 根据隔离人员Id查询隔离记录
 * @Date: 2022/9/1 11:24 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "根据隔离人员Id查询隔离记录VO")
public class QueryIsolationDetailByIdVO extends BaseId {

  @ApiModelProperty(value = "学号")
  private String code;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "体温（去掉小数点，即乘10后的结果）")
  private Integer temperature;


}
