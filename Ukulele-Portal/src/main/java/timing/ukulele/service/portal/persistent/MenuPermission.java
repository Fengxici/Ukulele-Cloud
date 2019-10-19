package timing.ukulele.service.portal.persistent;

import lombok.Data;

/**
 * •角色菜单权限
 * •@className: MenuPermission
 * •@author: 吕自聪
 * •@date: 2019/10/19
 */
@Data
public class MenuPermission {
    private Long id;
    private String router;
    private String ability;
}
