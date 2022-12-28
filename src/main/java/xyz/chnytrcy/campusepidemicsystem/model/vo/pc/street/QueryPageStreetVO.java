package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.street;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.street
 * @ClassName: QueryPageVO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/8/28 7:57 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询风险地区")
public class QueryPageStreetVO extends Base {

  @ApiModelProperty("街道全称")
  private String fullName;

  @ApiModelProperty("风险地区(低：0，中：1，高：2)")
  private Integer riskLevel;

  @ApiModelProperty("街道编号")
  private Integer code;

  @ApiModelProperty("最后更新时间")
  @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
  private LocalDateTime updateTime;

}
