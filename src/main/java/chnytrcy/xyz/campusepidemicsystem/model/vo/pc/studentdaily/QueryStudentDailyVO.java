package chnytrcy.xyz.campusepidemicsystem.model.vo.pc.studentdaily;

import chnytrcy.xyz.campusepidemicsystem.config.basic.model.BaseId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ProjectName: campus-epidemic-system
 * @Package: chnytrcy.xyz.campusepidemicsystem.model.vo.pc.studentdaily
 * @ClassName: QueryStudentDailyVO
 * @Author: ChnyTrcy
 * @Description: 查询学生日常打卡VO
 * @Date: 2022/10/7 13:35
 * @Version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询学生日常打卡VO")
public class QueryStudentDailyVO extends BaseId {

  @ApiModelProperty(value = "体温是否异常")
  private Integer temperatureAbnormal;

  @ApiModelProperty(value = "是否有异常症状")
  private Integer abnormalSymptoms;

  @ApiModelProperty(value = "健康码颜色（0:绿，1:黄，2:红）")
  private Integer healthCode;

  @ApiModelProperty(value = "承诺（0:接受，1:拒绝）")
  private Integer promise;

  @ApiModelProperty(value = "精确地区")
  private String preciseArea;

  @ApiModelProperty(value = "时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  private String createTime;
}
