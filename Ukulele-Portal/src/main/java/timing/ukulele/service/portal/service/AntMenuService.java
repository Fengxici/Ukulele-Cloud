package timing.ukulele.service.portal.service;

import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.model.persistent.AntMenu;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.AntMenuMapper;

import java.util.List;

@Service
public class AntMenuService extends BaseService<AntMenu> {
    public List<AntMenu> findMenuByRoleName(String role) {
        return ((AntMenuMapper) this.baseMapper).findMenuByRoleName(role);
    }

    public Boolean deleteRoleMenu(Long roleId, Long menuId) {
        return ((AntMenuMapper) this.baseMapper).deleteRoleMenu(roleId, menuId) >= 0;
    }

    public Boolean addRoleMenu(Long roleId, Long menuId) {
        return ((AntMenuMapper) this.baseMapper).addRoleMenu(roleId, menuId) > 0;
    }

    public List<AntMenu> getMenuByUserId(Long userId) {
        return ((AntMenuMapper) this.baseMapper).getMenuByUserId(userId);
    }
}
