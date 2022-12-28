package xyz.chnytrcy.campusepidemicsystem.model.command.pc.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.IdCard;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.annotation.valid.Phone;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.teacher
 * @ClassName: UpdateTeacherCommand
 * @Author: ChnyTrcy
 * @Description: 修改教职工Command
 * @Date: 2022/8/28 2:20 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改教职工Command")
public class UpdateTeacherCommand extends Base {

  @ApiModelProperty(value = "唯一编号",required = true)
  @NotNull(message = "唯一编号不能为空")
  private Long id;

  @ApiModelProperty(value = "姓名",required = true)
  @NotBlank(message = "姓名不能为空")
  private String name;

  @ApiModelProperty(value = "院系编号",required = true)
  @NotBlank(message = "院系编号不能为空")
  private String deptCode;

  @ApiModelProperty(value = "联系方式")
  @Phone
  private String phone;

  @ApiModelProperty(value = "性别",required = true)
  @ListValue(vals = {1,2},message = "非法性别")
  private Integer sex;

  @ApiModelProperty(value = "身份证")
  @IdCard
  private String idCard;

  @ApiModelProperty("居住地址")
  private String address;
}
