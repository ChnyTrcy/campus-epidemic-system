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
 * @ClassName: AdminInformationDTO
 * @Author: ChnyTrcy
 * @Description: 管理员信息DTO
 * @Date: 2022/9/15 11:02 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("管理员信息DTO")
public class AdminInformationDTO extends BaseId {

  @ApiModelProperty("账户名")
  private String account;

  @ApiModelProperty("联系方式")
  private String phone;

  @ApiModelProperty("管辖院系名称")
  private String deptName;
}
