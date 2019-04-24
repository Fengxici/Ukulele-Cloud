package timing.ukulele.service.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.IAntIconFacade;
import timing.ukulele.facade.portal.model.persistent.AntIcon;
import timing.ukulele.service.portal.service.SysAntIconService;
import timing.ukulele.web.controller.BaseController;

import java.util.List;
import java.util.Map;

@RestController
public class AntIconController extends BaseController implements IAntIconFacade {
    private final SysAntIconService antIconService;

    @Autowired
    public AntIconController(SysAntIconService antIconService) {
        this.antIconService = antIconService;
    }

    @Override
    public ResponseData<AntIcon> get(Long id) {
        if(id==null||id<=0)
            return paraErrorResponse();
        return successResponse(this.antIconService.getById(id));
    }

    @Override
    public ResponseData<List<AntIcon>> getByParam(Map<String, Object> map) {
        return successResponse((List<AntIcon>)this.antIconService.listByMap(map));
    }

    @Override
    public ResponseData<Boolean> add(AntIcon sysAntIcon) {
       if(sysAntIcon==null||sysAntIcon.getId()!=null)
           return paraErrorResponse();
       return successResponse(this.antIconService.save(sysAntIcon));
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
       if(id==null||id<=0)
           return paraErrorResponse();
       return successResponse(this.antIconService.removeById(id));
    }

    @Override
    public ResponseData<Boolean> edit(AntIcon sysAntIcon) {
        if(sysAntIcon==null||sysAntIcon.getId()==null)
            return paraErrorResponse();
        return successResponse(this.antIconService.saveOrUpdate(sysAntIcon));
    }
}
