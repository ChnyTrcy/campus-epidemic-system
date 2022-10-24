package chnytrcy.xyz.campusepidemicsystem.model.entity;

import chnytrcy.xyz.campusepidemicsystem.config.basic.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.entity
 * @ClassName: IsolationDetail
 * @Author: ChnyTrcy
 * @Description: 隔离记录实体类
 * @Date: 2022/8/24 3:53 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("isolation_detail")
@ApiModel("隔离记录实体类")
public class IsolationDetail extends BaseEntity {

  @ApiModelProperty(value = "学号",required = true)
  private String code;

  @ApiModelProperty(value = "姓名",required = true)
  private String name;

  @ApiModelProperty(value = "体温（去掉小数点，即乘10后的结果）",required = true)
  private Integer temperature;

  @ApiModelProperty(value = "关联隔离人员Id",required = true)
  private Long relId;
}
