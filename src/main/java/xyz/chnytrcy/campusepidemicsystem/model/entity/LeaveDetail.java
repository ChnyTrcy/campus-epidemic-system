package xyz.chnytrcy.campusepidemicsystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @ClassName: LeaveDetail
 * @Author: ChnyTrcy
 * @Description: 离校申请过程实体类
 * @Date: 2022/9/6 11:06 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("leave_detail")
@ApiModel("离校申请过程实体类")
@EnumTransformation
public class LeaveDetail extends BaseEntity {

  @ApiModelProperty("关联ID")
  private Long relId;

  @ApiModelProperty("1、学生提出，2、管理员回复，3、学生再次回复，4、管理员终审")
  @EnumValue("TYPE")
  private Integer type;

  @ApiModelProperty("消息")
  private String message;

}
