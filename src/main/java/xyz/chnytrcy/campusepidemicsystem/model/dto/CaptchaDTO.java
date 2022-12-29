package xyz.chnytrcy.campusepidemicsystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.dto
 * @ClassName: CaptchaDTO
 * @Author: ChnyTrcy
 * @Description: 验证码DTO
 * @Date: 2022/11/11 16:16
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("验证码DTO")
public class CaptchaDTO extends Base {

  @ApiModelProperty("验证码内容")
  private String text;

  @ApiModelProperty("创建时间")
  private LocalDateTime startTime;

}
