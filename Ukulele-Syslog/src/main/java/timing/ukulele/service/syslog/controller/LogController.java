package timing.ukulele.service.syslog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.syslog.api.ILogFacade;
import timing.ukulele.facade.syslog.model.persistent.SysLog;
import timing.ukulele.service.syslog.service.SysLogService;
import timing.ukulele.web.controller.BaseController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 日志
 * </p>
 */
@RestController
public final class LogController extends BaseController implements ILogFacade {

    private final SysLogService sysLogService;

    @Autowired
    public LogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @Override
    public ResponseData<Boolean> add( SysLog sysLog) {
        if (sysLog == null || sysLog.getId() != null)
            return paraErrorResponse();
        if (sysLogService.save(sysLog))
            return successResponse(Boolean.TRUE);
        return failResponse();
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(sysLogService.removeById(id));
    }

    @Override
    public ResponseData<List<SysLog>> getByParam(Map<String, Object> map) {
        List<SysLog> list = (List) sysLogService.listByMap(map);
        return successResponse(list);
    }
}
