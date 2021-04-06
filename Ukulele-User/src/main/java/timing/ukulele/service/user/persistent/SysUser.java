package timing.ukulele.service.user.persistent;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseIdWorkerModel;

/**
 * 用户表
 * @author fengxici
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class SysUser extends BaseIdWorkerModel {
    /**
     * 用户名
     */
    @TableField("username_")
    private String username;
    /**
     * 密码
     */
    @TableField("password_")
    private String password;
    /**
     * 电话
     */
    @TableField("phone_")
    private String phone;
    /**
     * 头像
     */
    @TableField("avatar_")
    private String avatar;
    /**
     * 标签
     */
    @TableField("label_")
    private String label;

}
