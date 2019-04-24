package timing.ukulele.service.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.IAntMenuFacade;
import timing.ukulele.facade.portal.model.data.AntMenuTree;
import timing.ukulele.facade.portal.model.persistent.AntMenu;
import timing.ukulele.service.portal.service.SysMenuAntService;
import timing.ukulele.web.controller.BaseController;

import java.util.List;
import java.util.Map;

@RestController
public final class MenuAntController extends BaseController implements IAntMenuFacade {
    private final SysMenuAntService menuAntService;

    @Autowired
    public MenuAntController(SysMenuAntService menuAntService) {
        this.menuAntService = menuAntService;
    }

    @Override
    public ResponseData<AntMenu> get(Long id) {
        if(id==null||id<=0)
            return paraErrorResponse();
        return  successResponse(this.menuAntService.getById(id));
    }

    @Override
    public ResponseData<List<AntMenu>> getByParam(Map<String, Object> map) {
        return successResponse((List<AntMenu>)this.menuAntService.listByMap(map));
    }

    @Override
    public ResponseData<List<AntMenuTree>> getDeptTree() {
        return null;
    }

    @Override
    public ResponseData<Boolean> add(AntMenu sysMenuAnt) {
        if(sysMenuAnt==null||sysMenuAnt.getId()!=null)
            return paraErrorResponse();
        return successResponse(this.menuAntService.save(sysMenuAnt));
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
        if(id==null||id<=0)
            return paraErrorResponse();
        return successResponse(this.menuAntService.removeById(id));
    }

    @Override
    public ResponseData<Boolean> edit(AntMenu sysMenuAnt) {
        if(sysMenuAnt==null||sysMenuAnt.getId()==null)
            return paraErrorResponse();
        return successResponse(this.menuAntService.saveOrUpdate(sysMenuAnt));
    }
}
