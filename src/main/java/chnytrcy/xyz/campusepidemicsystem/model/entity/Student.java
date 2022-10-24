package chnytrcy.xyz.campusepidemicsystem.model.entity;

import chnytrcy.xyz.campusepidemicsystem.config.basic.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.entity
 * @ClassName: Student
 * @Author: ChnyTrcy
 * @Description: 学生实体类
 * @Date: 2022/8/24 8:55 AM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生实体类")
public class Student extends BaseEntity {

  @ApiModelProperty(value = "学号",required = true)
  private String code;

  @ApiModelProperty(value = "姓名",required = true)
  private String name;

  @ApiModelProperty(value = "院系编号",required = true)
  @TableField("dept_code")
  private String deptCode;

  @ApiModelProperty(value = "院系名称",required = true)
  @TableField("dept_name")
  private String deptName;

  @ApiModelProperty(value = "专业编号",required = true)
  @TableField("major_code")
  private String majorCode;

  @ApiModelProperty(value = "专业名称",required = true)
  @TableField("major_name")
  private String majorName;

  @ApiModelProperty(value = "班级编号",required = true)
  @TableField("class_code")
  private String classCode;

  @ApiModelProperty(value = "班级名",required = true)
  @TableField("class_name")
  private String className;

  @ApiModelProperty("联系方式")
  private String phone;

  @ApiModelProperty("紧急联系人")
  @TableField("emergency_contact")
  private String emergencyContact;

  @ApiModelProperty("紧急联系方式")
  @TableField("emergency_phone")
  private String emergencyPhone;

  @ApiModelProperty("性别（1:男，2:女）")
  private Integer sex;

  @ApiModelProperty("身份证号")
  @TableField("id_card")
  private String idCard;

  @ApiModelProperty("居住地址")
  private String address;

  @ApiModelProperty("信誉值")
  private Integer reputation;
}
