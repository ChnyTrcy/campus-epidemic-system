package chnytrcy.xyz.campusepidemicsystem.config.basic.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

/**
 * @ClassName BaseEntity
 * @Description
 * @Author chnytrcy
 * @DATE 2022/8/20 4:32 PM
 * @Version 1.0
 */
@ApiModel("实体类基本字段")
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends BaseEntityIdField{

  @ApiModelProperty("创建时间")
  @TableField(
      value = "create_time",
      jdbcType = JdbcType.TIMESTAMP,
      fill = FieldFill.INSERT
  )
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime createTime;

  @ApiModelProperty("创建用户")
  @TableField(
      value = "create_user",
      fill = FieldFill.INSERT
  )
  protected String createUser;

  @ApiModelProperty("更新时间")
  @TableField(
      value = "update_time",
      jdbcType = JdbcType.TIMESTAMP,
      fill = FieldFill.INSERT_UPDATE
  )
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  protected LocalDateTime updateTime;

  @ApiModelProperty("更新用户")
  @TableField(
      value = "update_user",
      fill = FieldFill.INSERT_UPDATE
  )
  protected String updateUser;

  @ApiModelProperty(
      value = "是否删除",
      example = "0"
  )
  @TableField(value = "is_delete")
  @TableLogic(value = "0",delval = "1")
  protected Integer isDelete;
}
