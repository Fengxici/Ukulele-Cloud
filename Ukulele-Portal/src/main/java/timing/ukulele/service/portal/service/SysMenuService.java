package timing.ukulele.service.portal.service;

import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.SysMenuMapper;

import java.util.List;

@Service
public class SysMenuService extends BaseService<SysMenu> {

    public List<SysMenu> findMenuByRoleName(String role) {
        return ((SysMenuMapper) this.baseMapper).findMenuByRoleName(role);
    }

    public Boolean deleteMenu(Long id) {
        // 删除当前节点
        SysMenu condition1 = new SysMenu();
        condition1.setId(id);
        condition1.setEnable(Boolean.FALSE);
        this.updateById(condition1);

        // 删除父节点为当前节点的节点
        SysMenu condition2 = new SysMenu();
        condition2.setParentId(id);
        SysMenu sysMenu = new SysMenu();
        sysMenu.setEnable(Boolean.FALSE);
        return this.save(sysMenu);
    }

    public Boolean updateMenuById(SysMenu sysMenu) {
        return this.updateById(sysMenu);
    }

    public Boolean deleteRoleMenu(Long roleId, Long menuId) {
        return ((SysMenuMapper) this.baseMapper).deleteRoleMenu(roleId, menuId) >= 0;
    }

    public Boolean addRoleMenu(Long roleId, Long menuId) {
        return ((SysMenuMapper) this.baseMapper).addRoleMenu(roleId, menuId) > 0;
    }

    public List<SysMenu> getMenuByUserId(Long userId) {
        return ((SysMenuMapper) this.baseMapper).getMenuByUserId(userId);
    }
}
