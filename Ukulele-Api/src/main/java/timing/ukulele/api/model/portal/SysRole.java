package timing.ukulele.api.model.portal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseModel;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role")
public class SysRole extends BaseModel {

    @TableField("role_name")
    private String roleName;
    @TableField("role_code")
    private String roleCode;
    @TableField("role_desc")
    private String roleDesc;
}
