package timing.ukulele.api.model.portal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseModel;


/**
 * <p>
 * 用户角色表
 * </p>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user_role")
public class SysUserRole extends BaseModel {

    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private Integer userId;
    /**
     * 角色ID
     */
    @TableId(type = IdType.INPUT)
    private Integer roleId;

}
