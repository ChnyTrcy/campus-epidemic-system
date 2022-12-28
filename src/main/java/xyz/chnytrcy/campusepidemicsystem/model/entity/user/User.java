package xyz.chnytrcy.campusepidemicsystem.model.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import xyz.chnytrcy.core.config.basic.entity.BaseEntity;

/**
 * @author chnytrcy
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户实体类",description = "")
public class User extends BaseEntity {

    @ApiModelProperty("用户名")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @TableField(exist = false)
    @ApiModelProperty("用户权限")
    private Set<Role> roles;

    public User(String account, String password, String phone) {
        this.account = account;
        this.password = password;
        this.phone = phone;
    }
}
