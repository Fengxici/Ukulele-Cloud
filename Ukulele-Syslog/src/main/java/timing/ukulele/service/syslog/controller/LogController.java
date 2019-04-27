package timing.ukulele.service.syslog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.syslog.api.ILogFacade;
import timing.ukulele.facade.syslog.model.persistent.SysLog;
import timing.ukulele.service.syslog.service.SysLogService;
import timing.ukulele.web.controller.BaseController;
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseData<List<SysLog>> getByParam(HttpServletRequest request) {
        SysLog log = Request2ModelUtil.covert(SysLog.class,request);
        List<SysLog> list =  sysLogService.list(new QueryWrapper<>(log));
        return successResponse(list);
    }

    @GetMapping("/log/page/{current}/{size}")
    public ResponseData<IPage<SysLog>> getPage(@PathVariable(name="current")int current,
                                               @PathVariable(name="size") int size, HttpServletRequest request) {
        SysLog log = Request2ModelUtil.covert(SysLog.class,request);
        if(size==0)size=10;
        if(current==0)current=1;
        return successResponse(this.sysLogService.getPage(log,current,size));
    }
}
