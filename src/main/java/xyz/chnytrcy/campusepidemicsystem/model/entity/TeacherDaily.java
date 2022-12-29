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
 * @ClassName: TeacherDaily
 * @Author: ChnyTrcy
 * @Description: 教职工日常打卡
 * @Date: 2022/8/24 5:00 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("teacher_daily")
@ApiModel("教职工日常打卡")
public class TeacherDaily extends BaseEntity {

  @ApiModelProperty(value = "工号",required = true)
  private String code;

  @ApiModelProperty(value = "打卡类型（0:上班，1:下班）",required = true)
  private Integer type;

  @ApiModelProperty(value = "打卡结果（0:绿，1:黄，2:红）",required = true)
  private Integer result;
}
