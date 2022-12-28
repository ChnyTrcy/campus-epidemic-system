package xyz.chnytrcy.campusepidemicsystem.model.vo.pc.leavedetail;

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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.vo.pc.leavedetail
 * @ClassName: LeaveDetailVO
 * @Author: ChnyTrcy
 * @Description: 出校交流VO
 * @Date: 2022/9/8 9:50 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("出校交流VO")
public class LeaveDetailVO extends BaseId {

  @ApiModelProperty("1、学生提出，2、管理员回复，3、学生再次回复，4、管理员终审")
  private Integer type;

  @ApiModelProperty("信息")
  private String message;

  @ApiModelProperty("时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime time;

}
