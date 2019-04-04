package timing.ukulele.api.model.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import timing.ukulele.persistence.model.BaseModel;

@Getter
@Setter
@TableName("role_module")
public class RoleModuleModel extends BaseModel {
    private Long roleId;
    private Long moduleId;
}
