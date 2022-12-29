package xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.utils.easyexcel.bo
 * @ClassName: DeptBO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/15 14:05
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptBO extends Base {

  @ExcelProperty(index = 0)
  @ApiModelProperty("院系编号")
  private String code;

  @ExcelProperty(index = 1)
  @ApiModelProperty("院系名称")
  private String name;

}
