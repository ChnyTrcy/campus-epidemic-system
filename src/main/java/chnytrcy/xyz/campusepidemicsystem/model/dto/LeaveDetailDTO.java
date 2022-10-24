package chnytrcy.xyz.campusepidemicsystem.model.dto;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.dto
 * @ClassName: LeaveDetailDTO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/8 9:35 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LeaveDetailDTO extends BaseId {

  @ApiModelProperty("1、学生提出，2、管理员回复，3、学生再次回复，4、管理员终审")
  private Integer type;

  @ApiModelProperty("信息")
  private String message;

  @ApiModelProperty("时间")
  private LocalDateTime time;

}
