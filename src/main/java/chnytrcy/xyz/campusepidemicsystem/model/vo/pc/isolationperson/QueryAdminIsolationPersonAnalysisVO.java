package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.isolationperson;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.pc.isolationperson
 * @ClassName: QueryAdminIsolationPersonAnalysisVO
 * @Author: ChnyTrcy
 * @Description: 统计分析本院系的隔离人员状态分布VO
 * @Date: 2022/10/7 14:12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("统计分析本院系的隔离人员状态分布VO")
public class QueryAdminIsolationPersonAnalysisVO extends Base {

  @ApiModelProperty(value = "已隔离")
  private Integer isolated;

  @ApiModelProperty(value = "待隔离")
  private Integer notificationNotQuarantined;

  @ApiModelProperty(value = "治疗中")
  private Integer treat;

  @ApiModelProperty(value = "隔离结束")
  private Integer end;

}
