package timing.ukulele.service.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.facade.portal.api.IMenuService;
import timing.ukulele.facade.portal.model.data.MenuTree;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.view.MenuVO;
import timing.ukulele.service.portal.service.SysMenuService;

import java.util.List;

@RestController
public final class MenuController implements IMenuService {

    private final SysMenuService sysMenuService;

    @Autowired
    public MenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }

    @Override
    public List<MenuVO> findMenuByRole(String s) {
        return null;
    }

    @Override
    public List<MenuTree> userMenu(String s) {
        return null;
    }

    @Override
    public List<MenuTree> getMenuTree() {
        return null;
    }

    @Override
    public List<Integer> roleTree(String s) {
        return null;
    }

    @Override
    public SysMenu menu(Long aLong) {
        return null;
    }

    @Override
    public ResponseVO menu(SysMenu sysMenu) {
        return null;
    }

    @Override
    public ResponseVO menuDel(Long aLong) {
        return null;
    }

    @Override
    public ResponseVO menuUpdate(SysMenu sysMenu) {
        return null;
    }

    @Override
    public ResponseData<List<SysMenu>> getMenuByUserId(Long aLong) {
        return null;
    }

}
