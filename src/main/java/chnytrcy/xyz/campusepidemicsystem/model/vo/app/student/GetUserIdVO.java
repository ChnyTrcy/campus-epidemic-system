package chnytrcy.xyz.campusepidemicsystem.model.vo.app.student;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.app.student
 * @ClassName: GetUserIdVO
 * @Author: ChnyTrcy
 * @Description: 获得学生Id VO
 * @Date: 2022/9/18 01:52
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("获得学生Id VO")
public class GetUserIdVO extends Base {

  @ApiModelProperty("id")
  private String id;

  @ApiModelProperty("时间")
  private LocalDateTime time;
}
