package chnytrcy.xyz.campusepidemicsystem.model.vo.app.student;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.app.student
 * @ClassName: ScanCodeGetStudentInformationVO
 * @Author: ChnyTrcy
 * @Description: 扫码查看该该学生的信息以及操作权限VO
 * @Date: 2022/9/13 2:41 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("扫码查看该该学生的信息以及操作权限VO")
public class ScanCodeGetStudentInformationVO extends BaseId {

  @ApiModelProperty("学号")
  private String code;

  @ApiModelProperty("姓名")
  private String name;

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

  @ApiModelProperty("隔离操作")
  private Boolean isolationOperation;

  @ApiModelProperty("增加体温操作")
  private Boolean addTemperature;

  @ApiModelProperty("解除隔离操作")
  private Boolean releaseQuarantine;
}
