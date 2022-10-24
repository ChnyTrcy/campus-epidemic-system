package chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance;

import chnytrcy.xyz.campusepidemicsystem.config.annotation.valid.ListValue;
import chnytrcy.xyz.campusepidemicsystem.config.basic.model.Base;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.command.app.feedbackacceptance
 * @ClassName: AddFeedbackAcceptanceByIsolationCommand
 * @Author: ChnyTrcy
 * @Description: 隔离人员提出反馈受理Command
 * @Date: 2022/10/18 10:35
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("隔离人员提出反馈受理Command")
public class AddFeedbackAcceptanceByIsolationCommand extends Base {

  @ApiModelProperty("反馈类型(1:物质反馈，2：意见反馈)")
  @NotNull(message = "反馈类型不能为空")
  @ListValue(vals = {1,2},message = "反馈类型数据错误")
  private Integer type;

  @ApiModelProperty("反馈内容")
  @NotBlank(message = "反馈内容不能为空")
  @Length(max = 100,message = "反馈内容超过上限")
  private String message;
}
