package chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.utils.easyexcel.bo
 * @ClassName: ClassBO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/16 14:00
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClassBO extends Base {

  @ExcelProperty(index = 0)
  @ApiModelProperty("院系编号")
  private String deptCode;

  @ExcelProperty(index = 1)
  @ApiModelProperty("专业编号")
  private String majorCode;

  @ExcelProperty(index = 2)
  @ApiModelProperty("班级编号")
  private String code;

  @ExcelProperty(index = 3)
  @ApiModelProperty("班级名称")
  private String name;

}
