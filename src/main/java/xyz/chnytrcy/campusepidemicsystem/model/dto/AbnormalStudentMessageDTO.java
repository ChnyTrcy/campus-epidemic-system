package xyz.chnytrcy.campusepidemicsystem.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.model.Base;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.dto
 * @ClassName: AbnormalStudentMessageDTO
 * @Author: ChnyTrcy
 * @Description: 隔离人员需要治疗消息复合体DTO
 * @Date: 2022/9/13 4:09 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel("隔离人员需要治疗消息复合体DTO")
public class AbnormalStudentMessageDTO extends Base {

  @ApiModelProperty(value = "姓名",required = true)
  private String name;

  @ApiModelProperty(value = "联系方式",required = true)
  private String phone;
}
