package xyz.chnytrcy.campusepidemicsystem.model.command.pc.teacher;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
 * @ClassName: AddTeacherCommand
 * @Author: ChnyTrcy
 * @Description: 添加教职工Command
 * @Date: 2022/8/25 10:10 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("添加教职工Command")
public class AddTeacherCommand extends Base {

  @ApiModelProperty(value = "工号",required = true)
  @NotBlank(message = "工号不能为空")
  @Size(min = 4,max = 4,message = "工号长度错误")
  private String code;

  @ApiModelProperty(value = "姓名",required = true)
  @NotBlank(message = "姓名不能为空")
  private String name;

  @ApiModelProperty(value = "院系编号",required = true)
  @NotBlank(message = "院系编号不能为空")
  @Size(min = 2,max = 2,message = "院系编号长度错误")
  private String deptCode;

  @ApiModelProperty(value = "联系方式",required = true)
  @NotBlank(message = "联系方式不能为空")
  @Phone
  private String phone;

  @ApiModelProperty(value = "性别（1:男，2:女）",required = true)
  @NotNull(message = "性别不能为空")
  @ListValue(vals = {1,2},message = "非法性别")
  private Integer sex;

  @ApiModelProperty(value = "身份证号")
  @IdCard
  private String idCard;

  @ApiModelProperty(value = "居住地址")
  private String address;
}
