package chnytrcy.xyz.campusepidemicsystem.model.command.pc.student.legitimate;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.student.legitimate
 * @ClassName: LegitimateStudent
 * @Author: ChnyTrcy
 * @Description: 学生的合法必备字段
 * @Date: 2022/8/25 11:00 AM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LegitimateStudent extends Base {

  @ApiModelProperty(value = "学号",required = true)
  @NotBlank(message = "学号不能为空")
  @Size(max = 10,min = 10,message = "学号长度错误")
  private String code;

  @ApiModelProperty(value = "院系编号",required = true)
  @NotBlank(message = "院系编号不能为空")
  private String deptCode;

  @ApiModelProperty(value = "专业编号",required = true)
  @NotBlank(message = "专业编号不能为空")
  private String majorCode;

  @ApiModelProperty(value = "班级编号",required = true)
  @NotBlank(message = "班级编号不能为空")
  private String classCode;
}
