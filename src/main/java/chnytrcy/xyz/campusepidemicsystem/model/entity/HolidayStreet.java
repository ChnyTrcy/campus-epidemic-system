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
 * @ClassName: HolidayStreet
 * @Author: ChnyTrcy
 * @Description: 寒暑假返校实体类
 * @Date: 2022/8/24 3:46 PM
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("holiday_street")
@ApiModel("寒暑假返校实体类")
public class HolidayStreet extends BaseEntity {

  @ApiModelProperty(value = "学号",required = true)
  private String code;

  @ApiModelProperty(value = "学生名称",required = true)
  private String name;

  @ApiModelProperty(value = "途径街道",required = true)
  private String streetList;
}
