package chnytrcy.xyz.campusepidemicsystem.model.command.app.leave;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.leave
 * @ClassName: BackSchoolCommand
 * @Author: ChnyTrcy
 * @Description: 返校Command
 * @Date: 2022/9/19 20:47
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel("返校Command")
public class BackSchoolCommand extends Base {

  @ApiModelProperty(value = "核酸结果(0：阴，1：阳)",required = true,example = "0")
  @NotNull(message = "核酸结果不能为空")
  private String nucleicAcid;

}
