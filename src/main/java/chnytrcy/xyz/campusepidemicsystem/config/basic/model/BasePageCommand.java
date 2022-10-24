package chnytrcy.xyz.campusepidemicsystem.config.basic.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @ClassName BasePage
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/20 4:21 PM
 * @Version 1.0
 */
@ApiModel("分页查询参数,只需传入pageNum和pageSize两个参数")
@Data
public class BasePageCommand extends Base{

  @ApiModelProperty(
      value = "页码",
      required = true,
      example = "1"
  )
  @NotNull(message = "当前页数不能为空")
  @Min(value = 1,message = "页码不能少于1")
  protected Integer pageNum;

  @ApiModelProperty(
      value = "每页Size",
      required = true,
      example = "10"
  )
  @NotNull(message = "PageSize不能为空")
  @Max(value = 100,message = "PageSize上限超出限制，请求错误")
  @Min(value = 5,message = "PageSize下限超出限制，请求错误")
  protected Integer pageSize;


}
