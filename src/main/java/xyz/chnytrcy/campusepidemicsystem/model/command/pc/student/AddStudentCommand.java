package xyz.chnytrcy.campusepidemicsystem.model.command.pc.student;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.campusepidemicsystem.model.command.pc.student.legitimate.LegitimateStudent;
import xyz.chnytrcy.core.config.annotation.valid.IdCard;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.annotation.valid.Phone;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.student
 * @ClassName: AddStudentCommand
 * @Author: ChnyTrcy
 * @Description: 单个增加学生Command
 * @Date: 2022/8/24 5:29 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("单个增加学生Command")
public class AddStudentCommand extends LegitimateStudent {

  @ApiModelProperty(value = "姓名",required = true)
  @NotBlank(message = "姓名不能为空")
  private String name;

  @ApiModelProperty("联系方式")
  @NotBlank(message = "联系方式不能为空")
  @Phone
  private String phone;

  @ApiModelProperty("紧急联系人")
  private String emergencyContact;

  @ApiModelProperty("紧急联系方式")
  @Phone
  private String emergencyPhone;

  @ApiModelProperty(value = "性别（1:男，2:女）",required = true)
  @NotNull(message = "性别不能为空")
  @ListValue(vals = {1,2},message = "非法性别")
  private Integer sex;

  @ApiModelProperty("身份证号")
  @IdCard
  private String idCard;

  @ApiModelProperty("居住地址")
  private String address;

}
