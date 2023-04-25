package xyz.chnytrcy.campusepidemicsystem.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;
import xyz.chnytrcy.core.config.basic.entity.EnumTransformation;
import xyz.chnytrcy.core.config.basic.entity.EnumValue;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.entity
 * @ClassName: StudentDaily
 * @Author: ChnyTrcy
 * @Description: 学生日常打卡实体表
 * @Date: 2022/10/7 10:22
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("学生日常打卡实体类")
@EnumTransformation
public class StudentDaily extends BaseEntity {

  @ApiModelProperty(value = "关联Id",required = true)
  private Long relId;

  @ApiModelProperty(value = "体温是否异常",required = true)
  @EnumValue("TEMPERATURE_ABNORMAL")
  private Integer temperatureAbnormal;

  @ApiModelProperty(value = "是否有异常症状",required = true)
  @EnumValue("ABNORMAL_SYMPTOMS")
  private Integer abnormalSymptoms;

  @ApiModelProperty(value = "健康码颜色（0:绿，1:黄，2:红）",required = true)
  @EnumValue("HEALTH_CODE")
  private Integer healthCode;

  @ApiModelProperty(value = "承诺（0:接受，1:拒绝）",required = true)
  @EnumValue("PROMISE")
  private Integer promise;

  @ApiModelProperty(value = "精确地区",required = true)
  private String preciseArea;

}
