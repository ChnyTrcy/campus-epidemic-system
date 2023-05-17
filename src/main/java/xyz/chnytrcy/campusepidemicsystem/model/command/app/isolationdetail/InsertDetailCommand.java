package xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.Max.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.annotation.valid.ListValue;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.command.app.isolationdetail
 * @ClassName: InsertDetailCommand
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2023/4/25 14:11
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("增加每日健康打卡记录Command")
public class InsertDetailCommand extends Base {

  @ApiModelProperty("体温是否正常")
  @ListValue(vals = {0,1},message = "体温为非法值")
  private Integer temperature;

  @ApiModelProperty("是否存在发烧等症状")
  @ListValue(vals = {0,1},message = "是否存在发烧等症状为非法值")
  private Integer abnormalSymptoms;

  @ApiModelProperty("核酸是否为阳性")
  @ListValue(vals = {0,1},message = "核酸是否为阳性伟非法值")
  private Integer healthCode;

  @ApiModelProperty("是否承诺")
  @ListValue(vals = {0,1},message = "非法承诺")
  private Integer promise;
}
