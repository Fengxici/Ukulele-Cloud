package timing.ukulele.api.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import timing.ukulele.persistence.model.BaseModel;

@Getter
@Setter
@TableName("user_role")
public class UserRoleModel extends BaseModel {
    private String userId;
    private String roleId;

}
