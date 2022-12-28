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
 * @ClassName: MajorBo
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/12/15 17:01
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorBO extends Base {

  @ExcelProperty(index = 0)
  @ApiModelProperty("院系编号")
  private String deptCode;

  @ExcelProperty(index = 1)
  @ApiModelProperty("专业编号")
  private String code;

  @ExcelProperty(index = 2)
  @ApiModelProperty("专业名称")
  private String name;

}
