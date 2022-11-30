package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo
 * @ClassName: TeacherBO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/30 14:12
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherBO implements Serializable {

  @ExcelProperty(index = 0)
  @ApiModelProperty("工号")
  private String code;

  @ExcelProperty(index = 1)
  @ApiModelProperty("姓名")
  private String name;

  @ExcelProperty(index = 2)
  @ApiModelProperty("院系编号")
  private String deptCode;

  @ExcelProperty(index = 3)
  @ApiModelProperty("联系方式")
  private String phone;

  @ExcelProperty(index = 4)
  @ApiModelProperty("身份证号")
  private String idCard;

  @ExcelProperty(index = 5)
  @ApiModelProperty("居住地址")
  private String address;

  @ExcelProperty(index = 6)
  @ApiModelProperty("性别")
  private Integer sex;

}
