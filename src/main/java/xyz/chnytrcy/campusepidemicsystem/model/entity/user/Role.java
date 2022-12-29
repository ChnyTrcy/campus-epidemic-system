package xyz.chnytrcy.campusepidemicsystem.model.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chnytrcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户权限类")
@TableName(value = "role")
public class Role {

    @TableId(type = IdType.AUTO,value = "role_id")
    @ApiModelProperty(value = "权限ID")
    private Integer roleId;

    @TableField(value = "role_name")
    @ApiModelProperty(value = "权限名称")
    private String roleName;

    @TableField(exist = false)
    private Set<Permission> permissions;
}
