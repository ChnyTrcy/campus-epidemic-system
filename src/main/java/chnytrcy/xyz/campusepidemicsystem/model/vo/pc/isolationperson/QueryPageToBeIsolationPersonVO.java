package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.isolationperson;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.isolationperson
 * @ClassName: QueryPageToBeIsolationPersonVO
 * @Author: ChnyTrcy
 * @Description: 分页查询待隔离人员
 * @Date: 2022/8/30 9:43 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询待隔离人员")
public class QueryPageToBeIsolationPersonVO extends BaseId {

  @ApiModelProperty(value = "学号")
  private String code;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "班级名称")
  private String className;

  @ApiModelProperty(value = "0:通知未隔离；1:已隔离，2:治疗中，3:隔离结束）")
  private Integer state;

}
