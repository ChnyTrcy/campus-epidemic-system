package chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.app.leave
 * @ClassName: LeaveDetailProcessVO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/10/7 16:24
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LeaveDetailProcessVO extends BaseId {

  @ApiModelProperty("1、学生提出，2、管理员回复，3、学生再次回复，4、管理员终审")
  private Integer type;

  @ApiModelProperty("信息")
  private String message;

  @ApiModelProperty("时间")
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime createTime;

}
