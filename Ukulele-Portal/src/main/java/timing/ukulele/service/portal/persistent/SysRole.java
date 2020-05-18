package timing.ukulele.service.portal.persistent;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import timing.ukulele.persistence.model.BaseIdWorkerModel;

/**
 * @author fengxici
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role")
public class SysRole extends BaseIdWorkerModel {
    private String roleName;
    private String roleCode;
    private String roleDesc;
}
