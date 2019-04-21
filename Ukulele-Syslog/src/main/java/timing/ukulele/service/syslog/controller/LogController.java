package timing.ukulele.service.syslog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/log")
public final class LogController extends BaseController implements ILogFacade {

    private final SysLogService sysLogService;

    @Autowired
    public LogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @Override
    public ResponseData<Boolean> add(SysLog sysLog) {
        if (sysLog == null || sysLog.getId() != null)
            return paraErrorResponse();
        if (sysLogService.save(sysLog))
            return successResponse(Boolean.TRUE);
        return failResponse();
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
        return null;
    }

    @Override
    public ResponseData<List<SysLog>> getByParam(Map<String, Object> map) {
        return null;
    }
}
