package timing.ukulele.service.portal.persistent;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fengxici
 */
@Data
public class AntRoleMenu implements Serializable {
    private Long roleId;
    private Long menuId;
    private String ability;
}
