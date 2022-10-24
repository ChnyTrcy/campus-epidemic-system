package chnytrcy.xyz.campusepidemicsystem.model.dto;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.dto
 * @ClassName: EpidemicInformationDTO
 * @Author: ChnyTrcy
 * @Description: 防疫人员基本信息DTO
 * @Date: 2022/9/15 11:11 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员基本信息DTO")
public class EpidemicInformationDTO extends BaseId {
  @ApiModelProperty(value = "工号")
  private String code;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "院系名称")
  private String deptName;

  @ApiModelProperty(value = "联系方式")
  private String phone;

  @ApiModelProperty(value = "性别（1:男，2:女）")
  private Integer sex;

  @ApiModelProperty(value = "身份证号")
  private String idCard;

  @ApiModelProperty(value = "居住地点")
  private String address;

}
