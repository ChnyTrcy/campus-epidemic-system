package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.student
 * @ClassName: StudentBO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/28 20:37
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBO extends Base {

  @ExcelProperty(index = 0)
  @ApiModelProperty("学号")
  private String code;

  @ExcelProperty(index = 4)
  @ApiModelProperty("姓名")
  private String name;

  @ExcelProperty(index = 1)
  @ApiModelProperty("院系编号")
  private String deptCode;

  @ExcelProperty(index = 2)
  @ApiModelProperty("专业编号")
  private String majorCode;

  @ExcelProperty(index = 3)
  @ApiModelProperty("班级编号")
  private String classCode;

  @ExcelProperty(index = 5)
  @ApiModelProperty("联系方式")
  private String phone;

  @ExcelProperty(index = 6)
  @ApiModelProperty("紧急联系人")
  private String emergencyContact;

  @ExcelProperty(index = 7)
  @ApiModelProperty("紧急联系方式")
  private String emergencyPhone;

  @ExcelProperty(index = 8)
  @ApiModelProperty("性别")
  private Integer sex;

  @ExcelProperty(index = 9)
  @ApiModelProperty("身份证号")
  private String idCard;

  @ExcelProperty(index = 10)
  @ApiModelProperty("居住地址")
  private String address;

}
