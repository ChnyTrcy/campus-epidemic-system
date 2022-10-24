package chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave
 * @ClassName: OutSchoolVO
 * @Author: ChnyTrcy
 * @Description: 出校VO
 * @Date: 2022/9/19 16:35
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("出校VO")
public class OutSchoolVO extends BaseId {

  @ApiModelProperty(value = "学生学号")
  private String code;

  @ApiModelProperty(value = "学生姓名")
  private String name;

  @ApiModelProperty(value = "请假类型（0:事假，1:病假）")
  private Integer type;

  @ApiModelProperty(value = "预计出校时间")
  private LocalDateTime estimateStartTime;

  @ApiModelProperty(value = "预计返校时间")
  private LocalDateTime estimateEndTime;

  @ApiModelProperty(value = "实际出校时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private LocalDateTime actualStartTime;

  @ApiModelProperty(value = "是否跨区")
  private Integer isStrideCounty;

  @ApiModelProperty(value = "是否跨天")
  private Integer isStrideDay;

  @ApiModelProperty(value = "是否允许出校")
  private Boolean isAllow;

  @ApiModelProperty(value = "目的地")
  private String target;

  @ApiModelProperty(value = "请假理由",required = true)
  private String reason;
}
