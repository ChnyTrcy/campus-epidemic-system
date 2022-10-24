package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.student;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.student
 * @ClassName: queryStudentByKeywordVO
 * @Author: ChnyTrcy
 * @Description: 按关键词查询学生VO
 * @Date: 2022/8/25 9:54 AM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("按关键词查询学生VO")
public class QueryStudentByKeywordVO extends BaseId {

  @ApiModelProperty("学号")
  private String code;

  @ApiModelProperty("姓名")
  private String name;

  @ApiModelProperty("院系名称")
  private String deptName;

  @ApiModelProperty("专业名称")
  private String majorName;

  @ApiModelProperty("班级名")
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
