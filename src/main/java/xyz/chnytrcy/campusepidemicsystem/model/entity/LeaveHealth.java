package xyz.chnytrcy.campusepidemicsystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model.entity
 * @ClassName: LeaveHealth
 * @Author: ChnyTrcy
 * @Description: 请假健康实体类
 * @Date: 2022/9/19 20:56
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("leave_health")
@ApiModel("请假健康实体类")
public class LeaveHealth extends BaseEntity {

  @ApiModelProperty(value = "关联ID",required = true)
  private Long relId;

  @ApiModelProperty(value = "健康码存储路径",required = true)
  private String healthImg;

  @ApiModelProperty(value = "核酸结果（0：阴，1：阳）",required = true)
  private Integer nucleicAcid;
}
