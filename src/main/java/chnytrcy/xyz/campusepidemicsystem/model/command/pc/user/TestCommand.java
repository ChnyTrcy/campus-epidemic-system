package chnytrcy.xyz.campusepidemicsystem.model.command.pc.user;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @ClassName TestCommand
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/21 7:39 PM
 * @Version 1.0
 */
@Data
public class TestCommand implements Serializable {

  @ListValue(vals = {1,2},message = "类型超出合理范围")
  private Integer type;

  @NotBlank(message = "姓名不能为空")
  private String name;
}
