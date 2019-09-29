package timing.ukulele.service.portal.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.constant.AbilityConstant;
import timing.ukulele.common.constant.RoleConstant;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.TreeUtil;
import timing.ukulele.data.portal.data.MenuTree;
import timing.ukulele.data.portal.view.AntRoleMenuEditVO;
import timing.ukulele.data.portal.view.MenuVO;
import timing.ukulele.facade.portal.IMenuFacade;
import timing.ukulele.service.portal.persistent.SysMenu;
import timing.ukulele.service.portal.service.SysMenuService;
import timing.ukulele.web.annotation.RequiredPermission;
import timing.ukulele.web.controller.BaseController;

import java.util.*;

@RestController
public final class MenuController extends BaseController implements IMenuFacade {
    private final String router = "/system/menu";
    private final SysMenuService sysMenuService;

    @Autowired
    public MenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<MenuVO> menu(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        SysMenu menu = this.sysMenuService.getById(id);
        if (menu == null)
            return successResponse();
        MenuVO vo = new MenuVO();
        BeanUtils.copyProperties(menu, vo);
        return successResponse(vo);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<Boolean> menu(MenuVO sysMenu) {
        if (sysMenu == null || sysMenu.getId() != null)
            return paraErrorResponse();
        SysMenu po = new SysMenu();
        BeanUtils.copyProperties(sysMenu, po);
        return successResponse(this.sysMenuService.save(po));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.DELETE, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<Boolean> menuDel(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.deleteMenu(id));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.EDIT, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<Boolean> menuUpdate(MenuVO sysMenu) {
        if (sysMenu == null || sysMenu.getId() == null)
            return paraErrorResponse();
        SysMenu po = new SysMenu();
        BeanUtils.copyProperties(sysMenu, po);
        return successResponse(this.sysMenuService.saveOrUpdate(po));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER, RoleConstant.ADMIN, RoleConstant.USER}, router = this.router)
    public ResponseData<List<MenuTree>> getMenuTree() {
        return null;
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER, RoleConstant.ADMIN, RoleConstant.USER}, router = this.router)
    public ResponseData<List<MenuVO>> getByParam(Map<String, Object> map) {
        Collection<SysMenu> menuList = this.sysMenuService.listByMap(map);
        if (CollectionUtils.isEmpty(menuList))
            return successResponse();
        List<MenuVO> voList = new ArrayList<>(menuList.size());
        menuList.forEach(po -> {
            MenuVO vo = new MenuVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER, RoleConstant.ADMIN, RoleConstant.USER}, router = this.router)
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
    @RequiredPermission(ability = AbilityConstant.DELETE, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = this.router)
    public ResponseData<Boolean> deleteRoleMenu(Long roleId, Long menuId) {
        if (roleId == null || roleId <= 0)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.deleteRoleMenu(roleId, menuId));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = this.router)
    public ResponseData<Boolean> addRoleMenu(Long roleId, Long menuId) {
        if (roleId == null || roleId <= 0 || menuId == null || menuId <= 0)
            return paraErrorResponse();
        return successResponse(this.sysMenuService.addRoleMenu(roleId, menuId));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.EDIT, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = this.router)
    public ResponseData<Boolean> editRoleMenu(AntRoleMenuEditVO vo) {
        return null;
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER, RoleConstant.ADMIN, RoleConstant.USER}, router = this.router)
    public ResponseData<List<MenuVO>> getMenuByUserId(Long userId) {
        if (userId == null || userId <= 0)
            return paraErrorResponse();
        List<SysMenu> poList = this.sysMenuService.getMenuByUserId(userId);
        if (CollectionUtils.isEmpty(poList))
            return successResponse();
        List<MenuVO> voList = new ArrayList<>(poList.size());
        poList.forEach(menu -> {
            MenuVO vo = new MenuVO();
            BeanUtils.copyProperties(menu, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER, RoleConstant.ADMIN, RoleConstant.USER}, router = this.router)
    public ResponseData<List<MenuTree>> getUserMenu(@RequestHeader("x-role-header") String roles) {
        if (StringUtils.isEmpty(roles))
            return paraErrorResponse();
        Set<SysMenu> all = new HashSet<>();
        Arrays.stream(roles.split(",")).forEach(role -> all.addAll(this.sysMenuService.findMenuByRoleName(role)));
        List<MenuTree> menuTreeList = new ArrayList<>();
        all.forEach(menu -> {
            if ("0".equals(menu.getType())) {
                MenuTree node = new MenuTree();
                BeanUtils.copyProperties(menu, node);
                menuTreeList.add(node);
            }
        });
        menuTreeList.sort(Comparator.comparing(MenuTree::getSort));
        //组织成tree
        return successResponse(TreeUtil.bulid(menuTreeList, -1L));
    }
}
