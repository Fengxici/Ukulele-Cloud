package timing.ukulele.service.portal.controller;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.TreeUtil;
import timing.ukulele.facade.portal.api.IAntMenuFacade;
import timing.ukulele.facade.portal.model.data.AntMenuTree;
import timing.ukulele.facade.portal.model.data.RoleMenuTree;
import timing.ukulele.facade.portal.model.view.AntIconVO;
import timing.ukulele.facade.portal.model.view.AntMenuVO;
import timing.ukulele.facade.portal.model.view.AntRoleMenuEditVO;
import timing.ukulele.facade.portal.model.view.RoleMenuEditVO;
import timing.ukulele.service.portal.persistent.AntIcon;
import timing.ukulele.service.portal.persistent.AntMenu;
import timing.ukulele.service.portal.service.AntIconService;
import timing.ukulele.service.portal.service.AntMenuService;
import timing.ukulele.web.controller.BaseController;

import java.util.*;
import java.util.zip.CheckedOutputStream;

@RestController
public final class AntMenuController extends BaseController implements IAntMenuFacade {
    private final AntMenuService antMenuService;

    private final AntIconService antIconService;

    @Autowired
    public AntMenuController(AntMenuService menuAntService, AntIconService antIconService) {
        this.antMenuService = menuAntService;
        this.antIconService = antIconService;
    }

    @Override
    public ResponseData<AntMenuVO> get(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        AntMenu menu = this.antMenuService.getById(id);
        if (menu == null)
            return successResponse();
        AntMenuVO vo = new AntMenuVO();
        BeanUtils.copyProperties(menu, vo);
        return successResponse(vo);
    }

    @Override
    public ResponseData<List<AntMenuVO>> getByParam(Map<String, Object> map) {
        Collection<AntMenu> poList = this.antMenuService.listByMap(map);
        if (CollectionUtils.isEmpty(poList))
            return successResponse();
        List<AntMenuVO> voList = new ArrayList<>(poList.size());
        poList.forEach(po -> {
            AntMenuVO vo = new AntMenuVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    public ResponseData<List<AntMenuTree>> getMenuTree() {
        List<AntMenu> list = antMenuService.list();
        return successResponse(createMenuTree(list));
    }

    @Override
    public ResponseData<Boolean> add(AntMenuVO sysMenuAnt) {
        if (sysMenuAnt == null || sysMenuAnt.getId() != null)
            return paraErrorResponse();
        AntMenu po = new AntMenu();
        BeanUtils.copyProperties(sysMenuAnt, po);
        return successResponse(this.antMenuService.save(po));
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return this.antMenuService.removeMenu(id);
    }

    @Override
    public ResponseData<Boolean> edit(AntMenuVO sysMenuAnt) {
        if (sysMenuAnt == null || sysMenuAnt.getId() == null)
            return paraErrorResponse();
        AntMenu po = new AntMenu();
        BeanUtils.copyProperties(sysMenuAnt, po);
        return successResponse(this.antMenuService.saveOrUpdate(po));
    }

    @Override
    public ResponseData<List<RoleMenuTree>> findAllMenuWithRole(Long roleId) {
        if (roleId == null)
            return paraErrorResponse();
        List<RoleMenuTree> list = this.antMenuService.findAllMenuWithRole(roleId);
        return successResponse(list);
    }

    @Override
    public ResponseData<List<AntMenuVO>> findMenuByRole(String role) {
        if (StringUtils.isEmpty(role))
            return paraErrorResponse();
        List<AntMenu> list = this.antMenuService.findMenuByRoleName(role);
        List<AntMenuVO> menus = new ArrayList<>();
        if (list != null)
            list.forEach(menu -> {
                AntMenuVO vo = new AntMenuVO();
                BeanUtils.copyProperties(menu, vo);
                if (StringUtils.isNotEmpty(menu.getAcl()))
                    vo.setAcl(JSON.parseArray(menu.getAcl(), String.class));
                menus.add(vo);
            });
        return successResponse(menus);
    }

    @Override
    public ResponseData<Boolean> deleteRoleMenu(Long roleId, Long menuId) {
        if (roleId == null || roleId <= 0)
            return paraErrorResponse();
        return successResponse(this.antMenuService.deleteRoleMenu(roleId, menuId));
    }

    @Override
    public ResponseData<Boolean> editRoleMenu(AntRoleMenuEditVO vo) {
        if (null == vo || null == vo.getRoleId())
            return paraErrorResponse();
        return antMenuService.editRoleMenu(vo);
    }

    @Override
    public ResponseData<List<AntMenuVO>> getMenuByUserId(Long userId) {
        if (userId == null || userId <= 0)
            return paraErrorResponse();
        List<AntMenu> poList = this.antMenuService.getMenuByUserId(userId);
        if (CollectionUtils.isEmpty(poList))
            return successResponse();
        List<AntMenuVO> voList = new ArrayList<>(poList.size());
        poList.forEach(po -> {
            AntMenuVO vo = new AntMenuVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    public ResponseData<List<AntMenuTree>> getUserMenu(@RequestHeader("x-role-header") String roles) {
        if (StringUtils.isEmpty(roles))
            return paraErrorResponse();
        Set<AntMenu> all = new HashSet<>();
        Arrays.stream(roles.split(",")).forEach(role -> all.addAll(this.antMenuService.findMenuByRoleName(role)));
        return successResponse(createMenuTree(all));
    }

    private List<AntMenuTree> createMenuTree(Collection<AntMenu> menuList) {
        List<AntMenuTree> menuTreeList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuList))
            menuList.forEach(menu -> {
                AntMenuTree node = new AntMenuTree();
                BeanUtils.copyProperties(menu, node);
                if (StringUtils.isNotEmpty(menu.getAcl()))
                    node.setAcl(JSON.parseArray(menu.getAcl(), String.class));
                if (menu.getIconId() != null) {
                    AntIcon icon = this.antIconService.getById(menu.getIconId());
                    AntIconVO antIconVO = new AntIconVO();
                    BeanUtils.copyProperties(icon, antIconVO);
                    node.setIcon(antIconVO);
                }
                menuTreeList.add(node);
            });
        return TreeUtil.buildByRecursive(menuTreeList, 0L);
    }
}
