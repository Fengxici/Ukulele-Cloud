package timing.ukulele.service.portal.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.view.MenuVO;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.SysMenuMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 */
@Service
public class SysMenuService extends BaseService<SysMenu> {

    private final SysMenuMapper sysMenuMapper;

    @Autowired
    public SysMenuService(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }

    public List<MenuVO> findMenuByRoleName(String role) {
        return sysMenuMapper.findMenuByRoleName(role);
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
}
