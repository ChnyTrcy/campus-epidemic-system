package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.teacherdaily;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.chnytrcy.core.config.basic.model.BaseId;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.pc.teacherdaily
 * @ClassName: QueryTeacherDailyHistoryVO
 * @Author: ChnyTrcy
 * @Description: 查看10天该教职工的历史打卡记录VO
 * @Date: 2022/9/6 10:56 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("查看10天该教职工的历史打卡记录VO")
public class QueryTeacherDailyHistoryVO extends BaseId {

  @ApiModelProperty(value = "打卡类型（0:上班，1:下班）")
  private Integer type;

  @ApiModelProperty(value = "打卡结果（0:绿，1:黄，2:红）")
  private Integer result;

  @ApiModelProperty(value = "打卡时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime createTime;
}
