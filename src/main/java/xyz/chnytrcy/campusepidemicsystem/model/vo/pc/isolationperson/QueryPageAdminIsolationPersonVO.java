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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.isolation
 * @ClassName: QueryPageIsolationPersonVO
 * @Author: ChnyTrcy
 * @Description: 管理员查看本院系隔离人员VO
 * @Date: 2022/8/30 10:27 AM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("管理员查看本院系隔离人员VO")
public class QueryPageAdminIsolationPersonVO extends BaseId {

  @ApiModelProperty("学号")
  private String code;

  @ApiModelProperty("姓名")
  private String name;

  @ApiModelProperty("院系名称")
  private String deptName;

  @ApiModelProperty("专业名称")
  private String majorName;

  @ApiModelProperty("班级名称")
  private String className;

  @ApiModelProperty("0:通知未隔离；1:已隔离，2:治疗中，3:隔离结束")
  private Integer state;

  @ApiModelProperty("隔离开始时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime startTime;

  @ApiModelProperty("隔离结束时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime endTime;

  @ApiModelProperty("防疫人员工号")
  private String preventionPersonnelCode;

  @ApiModelProperty("防疫人员姓名")
  private String preventionPersonnelName;

  @ApiModelProperty(value = "隔离地点")
  private String quarantineLocation;
}
