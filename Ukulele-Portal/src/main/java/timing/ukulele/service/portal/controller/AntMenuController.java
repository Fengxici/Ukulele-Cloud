package timing.ukulele.service.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public ResponseData<AntMenu> get(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.antMenuService.getById(id));
    }

    @Override
    public ResponseData<List<AntMenu>> getByParam(Map<String, Object> map) {
        return successResponse((List<AntMenu>) this.antMenuService.listByMap(map));
    }

    @Override
    public ResponseData<List<AntMenuTree>> getDeptTree() {
        return null;
    }

    @Override
    public ResponseData<Boolean> add(AntMenu sysMenuAnt) {
        if (sysMenuAnt == null || sysMenuAnt.getId() != null)
            return paraErrorResponse();
        return successResponse(this.antMenuService.save(sysMenuAnt));
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.antMenuService.removeById(id));
    }

    @Override
    public ResponseData<Boolean> edit(AntMenu sysMenuAnt) {
        if (sysMenuAnt == null || sysMenuAnt.getId() == null)
            return paraErrorResponse();
        return successResponse(this.antMenuService.saveOrUpdate(sysMenuAnt));
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
    public ResponseData<Boolean> addRoleMenu(Long roleId, Long menuId) {
        if (roleId == null || roleId <= 0 || menuId == null || menuId <= 0)
            return paraErrorResponse();
        return successResponse(this.antMenuService.addRoleMenu(roleId, menuId));
    }

    @Override
    public ResponseData<List<AntMenu>> getMenuByUserId(Long userId) {
        if (userId == null || userId <= 0)
            return paraErrorResponse();
        return successResponse(this.antMenuService.getMenuByUserId(userId));
    }

    @Override
    public ResponseData<List<AntMenuTree>> getUserMenu(@RequestHeader("x-role-header") String roles) {
        if (StringUtils.isEmpty(roles))
            return paraErrorResponse();
        Set<AntMenu> all = new HashSet<>();
        Arrays.stream(roles.split(",")).forEach(role -> all.addAll(this.antMenuService.findMenuByRoleName(role)));
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
    
     @GetMapping("/page/{current}/{size}")
    public ResponseData<IPage<AntMenuVO>> page(
            @PathVariable("current") int current,
            @PathVariable("size") int size,
            HttpServletRequest request) {
        if (current < 0) current = 1;
        if (size < 10) size = 10;
        AntMenuVO menuVO = Request2ModelUtil.covert(AntMenuVO.class, request);
        AntMenu menu = new AntMenu();
        if (menuVO != null)
            BeanUtils.copyProperties(menuVO, menu);
        IPage<AntMenu> page = antMenuService.getPage(menu, current, size);
        IPage<AntMenuVO> pageVO = new Page<>();
        if (page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            pageVO.setCurrent(page.getCurrent());
            pageVO.setPages(page.getPages());
            pageVO.setSize(page.getSize());
            pageVO.setTotal(page.getTotal());
            pageVO.setRecords(new ArrayList<>(page.getRecords().size()));
            page.getRecords().forEach(record -> {
                AntMenuVO vo = new AntMenuVO();
                BeanUtils.copyProperties(record, vo);
                pageVO.getRecords().add(vo);
            });
        }
        return successResponse(pageVO);
    }
}
