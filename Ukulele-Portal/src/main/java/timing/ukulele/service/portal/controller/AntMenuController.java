package timing.ukulele.service.portal.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.TreeUtil;
import timing.ukulele.facade.portal.api.IAntMenuFacade;
import timing.ukulele.facade.portal.model.data.AntMenuTree;
import timing.ukulele.facade.portal.model.persistent.AntIcon;
import timing.ukulele.facade.portal.model.persistent.AntMenu;
import timing.ukulele.facade.portal.model.view.AntIconVO;
import timing.ukulele.facade.portal.model.view.AntMenuVO;
import timing.ukulele.service.portal.service.AntIconService;
import timing.ukulele.service.portal.service.AntMenuService;
import timing.ukulele.web.controller.BaseController;

import java.util.*;

@RestController
public final class AntMenuController extends BaseController implements IAntMenuFacade {
    private final AntMenuService menuAntService;

    private final AntIconService antIconService;

    @Autowired
    public AntMenuController(AntMenuService menuAntService, AntIconService antIconService) {
        this.menuAntService = menuAntService;
        this.antIconService = antIconService;
    }

    @Override
    public ResponseData<AntMenu> get(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.menuAntService.getById(id));
    }

    @Override
    public ResponseData<List<AntMenu>> getByParam(Map<String, Object> map) {
        return successResponse((List<AntMenu>) this.menuAntService.listByMap(map));
    }

    @Override
    public ResponseData<List<AntMenuTree>> getDeptTree() {
        return null;
    }

    @Override
    public ResponseData<Boolean> add(AntMenu sysMenuAnt) {
        if (sysMenuAnt == null || sysMenuAnt.getId() != null)
            return paraErrorResponse();
        return successResponse(this.menuAntService.save(sysMenuAnt));
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.menuAntService.removeById(id));
    }

    @Override
    public ResponseData<Boolean> edit(AntMenu sysMenuAnt) {
        if (sysMenuAnt == null || sysMenuAnt.getId() == null)
            return paraErrorResponse();
        return successResponse(this.menuAntService.saveOrUpdate(sysMenuAnt));
    }

    @Override
    public ResponseData<List<AntMenuVO>> findMenuByRole(String role) {
        if (StringUtils.isEmpty(role))
            return paraErrorResponse();
        List<AntMenu> list = this.menuAntService.findMenuByRoleName(role);
        List<AntMenuVO> menus = new ArrayList<>();
        if (list != null)
            list.forEach(menu -> {
                AntMenuVO vo = new AntMenuVO();
                BeanUtils.copyProperties(menu, vo);
                menus.add(vo);
            });
        return successResponse(menus);
    }

    @Override
    public ResponseData<Boolean> deleteRoleMenu(Long roleId, Long menuId) {
        if (roleId == null || roleId <= 0)
            return paraErrorResponse();
        return successResponse(this.menuAntService.deleteRoleMenu(roleId, menuId));
    }

    @Override
    public ResponseData<Boolean> addRoleMenu(Long roleId, Long menuId) {
        if (roleId == null || roleId <= 0 || menuId == null || menuId <= 0)
            return paraErrorResponse();
        return successResponse(this.menuAntService.addRoleMenu(roleId, menuId));
    }

    @Override
    public ResponseData<List<AntMenu>> getMenuByUserId(Long userId) {
        if (userId == null || userId <= 0)
            return paraErrorResponse();
        return successResponse(this.menuAntService.getMenuByUserId(userId));
    }

    @Override
    public ResponseData<List<AntMenuTree>> getUserMenu(@RequestHeader("x-role-header") String roles) {
        if (StringUtils.isEmpty(roles))
            return paraErrorResponse();
        Set<AntMenu> all = new HashSet<>();
        Arrays.stream(roles.split(",")).forEach(role -> all.addAll(this.menuAntService.findMenuByRoleName(role)));
        List<AntMenuTree> menuTreeList = new ArrayList<>();
        all.forEach(menu -> {
            AntMenuTree node = new AntMenuTree();
            BeanUtils.copyProperties(menu, node);
            if (menu.getIconId() != 0) {
                AntIcon icon = this.antIconService.getById(menu.getIconId());
                AntIconVO antIconVO = new AntIconVO();
                BeanUtils.copyProperties(icon, antIconVO);
                node.setIcon(antIconVO);
            }
            menuTreeList.add(node);
        });
        //组织成tree
        return successResponse(TreeUtil.buildByRecursive(menuTreeList, 0L));
    }
}