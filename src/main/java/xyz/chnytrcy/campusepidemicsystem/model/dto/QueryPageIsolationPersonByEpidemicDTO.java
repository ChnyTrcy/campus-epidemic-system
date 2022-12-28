package xyz.chnytrcy.campusepidemicsystem.model.dto;

import xyz.chnytrcy.campusepidemicsystem.model.entity.FeedbackAcceptance;
import xyz.chnytrcy.campusepidemicsystem.model.entity.IsolationPerson;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.dto
 * @ClassName: QueryPageIsolationPersonByEpidemicDTO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/9/10 5:19 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("防疫人员查看所属隔离人员基本信息以及反馈受理表")
public class QueryPageIsolationPersonByEpidemicDTO extends IsolationPerson {

  @ApiModelProperty("反馈受理")
  List<FeedbackAcceptance> list;

}
