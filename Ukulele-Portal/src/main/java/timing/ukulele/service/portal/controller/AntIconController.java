package timing.ukulele.service.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.IAntIconFacade;
import timing.ukulele.facade.portal.model.persistent.AntIcon;
import timing.ukulele.service.portal.service.AntIconService;
import timing.ukulele.web.controller.BaseController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class AntIconController extends BaseController implements IAntIconFacade {
    private final AntIconService antIconService;

    @Autowired
    public AntIconController(AntIconService antIconService) {
        this.antIconService = antIconService;
    }

    @Override
    public ResponseData<AntIcon> get(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.antIconService.getById(id));
    }

    @Override
    public ResponseData<List<AntIcon>> getByParam(HttpServletRequest request) {
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, null);
        return successResponse((List<AntIcon>) this.antIconService.listByMap(map));
    }

    @Override
    public ResponseData<Boolean> add(AntIcon sysAntIcon) {
        if (sysAntIcon == null || sysAntIcon.getId() != null)
            return paraErrorResponse();
        return successResponse(this.antIconService.save(sysAntIcon));
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.antIconService.removeById(id));
    }

    @Override
    public ResponseData<Boolean> edit(AntIcon sysAntIcon) {
        if (sysAntIcon == null || sysAntIcon.getId() == null)
            return paraErrorResponse();

        return successResponse(this.antIconService.saveOrUpdate(sysAntIcon));
    }

    @GetMapping("/page")
    public ResponseData<IPage<AntIcon>> getPage(HttpServletRequest request) {
        Map<String, Object> map = WebUtils.getParametersStartingWith(request, null);
        return successResponse(this.antIconService.getPage(map));
    }
}
