package xyz.chnytrcy.campusepidemicsystem.model.command.app.student;

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
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.student
 * @ClassName: ScanCodeGetStudentInformationCommand
 * @Author: ChnyTrcy
 * @Description: 扫码获取学生的信息以及操作权限Command
 * @Date: 2022/9/13 2:23 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("扫码获取学生的信息以及操作权限Command")
public class ScanCodeGetStudentInformationCommand extends BaseId {

  @ApiModelProperty("二维码生成时间")
  private LocalDateTime time;

}
