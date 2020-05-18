package timing.ukulele.service.portal.service;

import org.springframework.stereotype.Service;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.SysMenuMapper;
import timing.ukulele.service.portal.persistent.SysMenu;

import java.util.List;

/**
 * @author fengxici
 */
@Service
public class SysMenuService extends BaseService<SysMenuMapper, SysMenu> {

    public List<SysMenu> findMenuByRoleName(String role) {
        return this.getBaseMapper().findMenuByRoleName(role);
    }

    public Boolean deleteMenu(Long id) {
        // 删除当前节点
        SysMenu condition1 = new SysMenu();
        condition1.setId(id);
        condition1.setDeleted(Boolean.TRUE);
        this.updateById(condition1);

        // 删除父节点为当前节点的节点
        SysMenu condition2 = new SysMenu();
        condition2.setParentId(id);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setDeleted(Boolean.TRUE);
        return this.save(sysMenu);
    }

    public Boolean deleteRoleMenu(Long roleId, Long menuId) {
        return this.getBaseMapper().deleteRoleMenu(roleId, menuId) >= 0;
    }

    public Boolean addRoleMenu(Long roleId, Long menuId) {
        return this.getBaseMapper().addRoleMenu(roleId, menuId) > 0;
    }

    public List<SysMenu> getMenuByUserId(Long userId) {
        return this.getBaseMapper().getMenuByUserId(userId);
    }
}
