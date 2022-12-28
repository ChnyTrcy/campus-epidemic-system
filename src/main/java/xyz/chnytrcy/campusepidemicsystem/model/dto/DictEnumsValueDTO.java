package xyz.chnytrcy.campusepidemicsystem.model.dto;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.dto
 * @ClassName: DictEnumsValueDTO
 * @Author: ChnyTrcy
 * @Description:
 * @Date: 2022/11/8 19:11
 * @Version: 1.0
 */
@Setter
@Getter
@NoArgsConstructor
public class DictEnumsValueDTO implements Serializable {

  @ApiModelProperty("枚举名")
  private String name;

  @ApiModelProperty("枚举值")
  private String code;

  @ApiModelProperty("枚举描述")
  private String desc;

  @ApiModelProperty("AppKey组描述")
  private String appKeyType;

  @ApiModelProperty("AppKey组")
  private String appKeyTypeCode;

  @ApiModelProperty("复合AppKey")
  private String appKeyId;
}
