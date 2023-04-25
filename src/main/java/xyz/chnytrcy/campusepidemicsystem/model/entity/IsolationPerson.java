package xyz.chnytrcy.campusepidemicsystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;
import xyz.chnytrcy.core.config.basic.entity.EnumTransformation;
import xyz.chnytrcy.core.config.basic.entity.EnumValue;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: xyz.chnytrcy.campusepidemicsystem.model
 * @ClassName: IsolationPerson
 * @Author: ChnyTrcy
 * @Description: 隔离人员实体类
 * @Date: 2022/8/24 4:27 PM
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("isolation_person")
@ApiModel("隔离人员实体类")
@EnumTransformation
public class IsolationPerson extends BaseEntity {

  @ApiModelProperty(value = "学号",required = true)
  private String code;

  @ApiModelProperty(value = "关联学生Id",required = true)
  private Long relId;

  @ApiModelProperty(value = "姓名",required = true)
  private String name;

  @ApiModelProperty(value = "院系编号",required = true)
  private String deptCode;

  @ApiModelProperty(value = "院系名字",required = true)
  private String deptName;

  @ApiModelProperty(value = "专业编号",required = true)
  private String majorCode;

  @ApiModelProperty(value = "专业名称",required = true)
  private String majorName;

  @ApiModelProperty(value = "班级编号",required = true)
  private String classCode;

  @ApiModelProperty(value = "班级名称",required = true)
  private String className;

  @ApiModelProperty(value = "0:通知未隔离；1:已隔离，2:治疗中，3:隔离结束）",
      required = true)
  @EnumValue("STATE")
  private Integer state;

  @ApiModelProperty(value = "隔离开始时间",required = true)
  private LocalDateTime startTime;

  @ApiModelProperty(value = "隔离结束时间",required = true)
  private LocalDateTime endTime;

  @ApiModelProperty(value = "防疫人员工号",required = true)
  private String preventionPersonnelCode;

  @ApiModelProperty(value = "防疫人员姓名",required = true)
  private String preventionPersonnelName;

  @ApiModelProperty(value = "隔离地点",required = true,example = "校园集中隔离点A")
  private String quarantineLocation;
}
