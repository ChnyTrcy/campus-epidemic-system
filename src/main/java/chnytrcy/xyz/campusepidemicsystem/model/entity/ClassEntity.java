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
 * @ClassName: Class
 * @Author: ChnyTrcy
 * @Description: 班级实体类
 * @Date: 2022/8/24 5:52 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("班级实体类")
@TableName("class")
public class ClassEntity extends BaseEntity {

  @ApiModelProperty(value = "班级全称",required = true)
  private String name;

  @ApiModelProperty(value = "班级编号",required = true)
  private String code;

  @ApiModelProperty(value = "学年全写",required = true)
  private String year;

  @ApiModelProperty(value = "学年简写",required = true)
  private String yearHalf;

  @ApiModelProperty(value = "父级院系编号",required = true)
  private String deptCode;

  @ApiModelProperty(value = "父级专业编号",required = true)
  private String majorCode;
}
