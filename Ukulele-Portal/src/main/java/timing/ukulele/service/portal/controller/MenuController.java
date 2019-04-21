package timing.ukulele.service.portal.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.IMenuFacade;
import timing.ukulele.facade.portal.model.data.MenuTree;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.view.MenuVO;
import timing.ukulele.service.portal.service.SysMenuService;
import timing.ukulele.web.controller.BaseController;

import java.util.*;

@RestController
public final class MenuController extends BaseController implements IMenuFacade {

    private final SysMenuService sysMenuService;

    @Autowired
    public MenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @Override
    public ResponseData<SysMenu> menu(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.getById(id));
    }

    @Override
    public ResponseData<Boolean> menu(SysMenu sysMenu) {
        if (sysMenu == null || sysMenu.getId() != null)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.save(sysMenu));
    }

    @Override
    public ResponseData<Boolean> menuDel(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.deleteMenu(id));
    }

    @Override
    public ResponseData<Boolean> menuUpdate(SysMenu sysMenu) {
        if (sysMenu == null || sysMenu.getId() == null)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.saveOrUpdate(sysMenu));
    }

    @Override
    public ResponseData<List<MenuTree>> getMenuTree() {
        return null;
    }

    @Override
    public ResponseData<List<SysMenu>> getByParam(Map<String, Object> map) {
        return successResponse((ArrayList) this.sysMenuService.listByMap(map));
    }

    @Override
    public ResponseData<List<MenuVO>> findMenuByRole(String roleName) {
        List<MenuVO> list = new ArrayList<>();
        List<SysMenu> menus = this.sysMenuService.findMenuByRoleName(roleName);
        if (menus != null) {
            menus.forEach(menu -> {
                MenuVO vo = new MenuVO();
                BeanUtils.copyProperties(menu, vo);
                list.add(vo);
            });
        }
        return successResponse(list);
    }

    @Override
    public ResponseData<Boolean> deleteRoleMenu(Long roleId, Long menuId) {
        if (roleId == null || roleId <= 0)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.deleteRoleMenu(roleId, menuId));
    }

    @Override
    public ResponseData<Boolean> addRoleMenu(Long roleId, Long menuId) {
        if (roleId == null || roleId <= 0 || menuId == null || menuId <= 0)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.addRoleMenu(roleId, menuId));
    }

    @Override
    public ResponseData<List<SysMenu>> getMenuByUserId(Long userId) {
        if (userId == null || userId <= 0)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.getMenuByUserId(userId));
    }

    @Override
    public ResponseData<List<MenuTree>> getUserMenu(@RequestHeader("x-role-header") String roles) {
        if(StringUtils.isEmpty(roles))
            return paraErrorResponse();
        Set<SysMenu> all = new HashSet<>();
        Arrays.stream(roles.split(",")).forEach(role->all.addAll(this.sysMenuService.findMenuByRoleName(role)));
        List<MenuTree> menuTreeList = new ArrayList<>();
        all.forEach(menu->{
            if("0".equals(menu.getType())){
                MenuVO vo  = new MenuVO();
                BeanUtils.copyProperties(menu,vo);
                menuTreeList.add(new MenuTree(vo));
            }
        });
        menuTreeList.sort(Comparator.comparing(MenuTree::getSort));
        return successResponse(menuTreeList);
    }
}
