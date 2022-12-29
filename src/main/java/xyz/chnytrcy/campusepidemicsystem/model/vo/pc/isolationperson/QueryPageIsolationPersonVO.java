package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.isolationperson;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.isolationperson
 * @ClassName: QueryPageIsolationPersonVO
 * @Author: ChnyTrcy
 * @Description: 防疫人员分页查询已隔离人员VO
 * @Date: 2022/8/31 4:25 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员分页查询已隔离人员VO")
public class QueryPageIsolationPersonVO extends BaseId {

  @ApiModelProperty(value = "学号")
  private String code;

  @ApiModelProperty(value = "姓名")
  private String name;

  @ApiModelProperty(value = "专业名称")
  private String majorName;

  @ApiModelProperty(value = "班级名称")
  private String className;

  @ApiModelProperty(value = "0:通知未隔离；1:已隔离，2:治疗中，3:隔离结束）")
  private Integer state;

  @ApiModelProperty("隔离开始时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime startTime;

  @ApiModelProperty("隔离结束时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime endTime;

  @ApiModelProperty("隔离地点")
  private String quarantineLocation;

  @ApiModelProperty("最后一次体温(乘10)")
  private Integer temperature;

  @ApiModelProperty("最后一次体温时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime time;

}
