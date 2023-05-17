package xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail;

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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail
 * @ClassName: QueryIsolationTimeCommand
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2023/4/26 20:38
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询隔离人员隔离记录VO")
public class QueryIsolationTimeVO extends Base {

  @ApiModelProperty("开始隔离时间")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  private LocalDateTime startTime;

  @ApiModelProperty("结束隔离时间")
  @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
  private LocalDateTime endTime;

}
