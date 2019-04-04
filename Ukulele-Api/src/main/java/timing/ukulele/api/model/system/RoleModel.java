package timing.ukulele.api.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import timing.ukulele.persistence.model.BaseModel;

import java.util.List;

@Getter
@Setter
@TableName("role_")
public class RoleModel extends BaseModel {
    private String roleCode;
    private String roleName;
    @TableField(exist = false)
    private List<ModuleModel> modules;
}
