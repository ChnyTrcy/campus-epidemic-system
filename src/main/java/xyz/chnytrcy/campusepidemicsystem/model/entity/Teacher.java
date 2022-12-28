package xyz.chnytrcy.campusepidemicsystem.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.entity
 * @ClassName: Teacher
 * @Author: ChnyTrcy
 * @Description: 教职工实体类
 * @Date: 2022/8/24 4:56 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("教职工实体类")
public class Teacher extends BaseEntity {

  @ApiModelProperty(value = "工号",required = true)
  private String code;

  @ApiModelProperty(value = "姓名",required = true)
  private String name;

  @ApiModelProperty(value = "院系编号",required = true)
  private String deptCode;

  @ApiModelProperty(value = "院系名称",required = true)
  private String deptName;

  @ApiModelProperty(value = "联系方式",required = true)
  private String phone;

  @ApiModelProperty(value = "性别（1:男，2:女）",required = true)
  private Integer sex;

  @ApiModelProperty(value = "身份证号")
  private String idCard;

  @ApiModelProperty(value = "居住地点")
  private String address;

  @ApiModelProperty(value = "防疫人员标记(0:否，1：是)",required = true)
  private Integer epidemicMark;
}
