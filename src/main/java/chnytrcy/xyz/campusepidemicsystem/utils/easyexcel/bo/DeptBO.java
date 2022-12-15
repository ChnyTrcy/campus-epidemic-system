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
 * @ClassName: DeptBO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/15 14:05
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptBO implements Serializable {

  @ExcelProperty(index = 0)
  @ApiModelProperty("院系编号")
  private String code;

  @ExcelProperty(index = 1)
  @ApiModelProperty("院系名称")
  private String name;

}
