package xyz.chnytrcy.campusepidemicsystem.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author chnytrcy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "权限")
@TableName(value = "permission")
public class Permission {

    @TableId(value = "permission_id")
    @ApiModelProperty(value = "权限id")
    private Integer permissionId;

    @TableField(value = "permission_name")
    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @ApiModelProperty(value = "权限具体能做什么")
    private String permission;
}
