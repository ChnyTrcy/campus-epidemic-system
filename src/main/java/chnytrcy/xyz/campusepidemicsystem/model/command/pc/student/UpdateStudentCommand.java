package chnytrcy.xyz.campusepidemicsystem.model.command.pc.student;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.IdCard;
import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.Phone;
import chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.legitimate.LegitimateStudent;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.student
 * @ClassName: UpdateStudentCommand
 * @Author: ChnyTrcy
 * @Description: 修改学生Command
 * @Date: 2022/8/25 10:50 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("修改学生Command")
public class UpdateStudentCommand extends LegitimateStudent {

  @ApiModelProperty(value = "唯一编号",required = true)
  @NotNull(message = "唯一编号不能为空")
  private Long id;

  @ApiModelProperty(value = "姓名",required = true)
  @NotBlank(message = "姓名不能为空")
  private String name;

  @ApiModelProperty("联系方式")
  @Phone
  private String phone;

  @ApiModelProperty("紧急联系人")
  private String emergencyContact;

  @ApiModelProperty("紧急联系方式")
  @Phone
  private String emergencyPhone;

  @ApiModelProperty(value = "性别（1:男，2:女）",required = true)
  @ListValue(vals = {1,2},message = "非法性别")
  private Integer sex;

  @ApiModelProperty("身份证号")
  @IdCard
  private String idCard;

  @ApiModelProperty("居住地址")
  private String address;

}
