package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.teacher
 * @ClassName: QueryEpidemicPersonVO
 * @Author: ChnyTrcy
 * @Description: 查询本院系防疫人员VO
 * @Date: 2022/8/30 11:04 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("查询本院系防疫人员VO")
public class QueryEpidemicPersonVO extends BaseId {

  @ApiModelProperty(value = "工号")
  private String code;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "联系方式")
  private String phone;

  @ApiModelProperty(value = "性别（1:男，2:女）")
  private Integer sex;

  @ApiModelProperty(value = "身份证号")
  private String idCard;

  @ApiModelProperty(value = "居住地点")
  private String address;

}
