package xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.app.isolationdetail
 * @ClassName: QueryPageIsolationDetailVO
 * @Author: ChnyTrcy
 * @Description: 分页查询隔离人员隔离记录VO
 * @Date: 2022/10/18 09:43
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("分页查询隔离人员隔离记录VO")
public class QueryPageIsolationDetailVO extends BaseId {

  @ApiModelProperty(value = "体温",required = true)
  private Integer temperature;

  @ApiModelProperty(value = "体温是否正常")
  private Boolean isNormal;

  @ApiModelProperty(value = "体温监测时间")
  private LocalDateTime createTime;

  @ApiModelProperty(value = "健康码颜色（0：绿，1：黄，2：红,3:未上传")
  private Integer healthCode;

}
