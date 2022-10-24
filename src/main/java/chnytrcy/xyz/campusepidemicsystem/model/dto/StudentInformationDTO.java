package chnytrcy.xyz.campusepidemicsystem.model.dto;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.dto
 * @ClassName: StudentInformationDTO
 * @Author: ChnyTrcy
 * @Description: 学生基本信息DTO
 * @Date: 2022/9/15 11:31 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("学生基本信息DTO")
public class StudentInformationDTO extends BaseId {

  @ApiModelProperty(value = "学号")
  private String code;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "院系名称")
  private String deptName;

  @ApiModelProperty(value = "专业名称")
  private String majorName;

  @ApiModelProperty(value = "班级名")
  private String className;

  @ApiModelProperty("联系方式")
  private String phone;

  @ApiModelProperty("紧急联系人")
  private String emergencyContact;

  @ApiModelProperty("紧急联系方式")
  private String emergencyPhone;

  @ApiModelProperty("性别（1:男，2:女）")
  private Integer sex;

  @ApiModelProperty("身份证号")
  private String idCard;

  @ApiModelProperty("居住地址")
  private String address;
}
