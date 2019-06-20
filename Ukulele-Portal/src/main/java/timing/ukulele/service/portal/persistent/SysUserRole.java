package timing.ukulele.service.portal.persistent;

import lombok.Data;

/**
 * <p>
 * 用户角色表
 * </p>
 */
@Data
public class SysUserRole {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

}
