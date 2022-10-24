package chnytrcy.xyz.campusepidemicsystem.config.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.io.Serializable;
import lombok.Data;

/**
 * @ClassName BaseEntityIdField
 * @Description 主键字段雪花算法生成
 * @Author chnytrcy
 * @DATE 2022/8/20 4:30 PM
 * @Version 1.0
 */
@Data
public class BaseEntityIdField implements Serializable {

  private static final long serialVersionUID = -3645936710283336508L;

  @TableId(
      value = "id",
      type = IdType.ASSIGN_ID
  )
  protected Long id;
}
