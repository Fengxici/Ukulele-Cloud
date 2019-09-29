package timing.ukulele.service.syslog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.constant.AbilityConstant;
import timing.ukulele.common.constant.RoleConstant;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.syslog.view.LogVO;
import timing.ukulele.facade.syslog.ILogFacade;
import timing.ukulele.service.syslog.persistent.SysLog;
import timing.ukulele.service.syslog.service.SysLogService;
import timing.ukulele.web.annotation.RequiredPermission;
import timing.ukulele.web.controller.BaseController;
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 日志
 * </p>
 */
@RestController
public final class LogController extends BaseController implements ILogFacade {
    private final String router = "/monitor/log";
    private final SysLogService sysLogService;

    @Autowired
    public LogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<Boolean> add(LogVO sysLog) {
        if (sysLog == null || sysLog.getId() != null)
            return paraErrorResponse();
        SysLog logPO = new SysLog();
        BeanUtils.copyProperties(sysLog, logPO);
        if (sysLogService.save(logPO))
            return successResponse(Boolean.TRUE);
        return failResponse();
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.DELETE, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(sysLogService.removeById(id));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<List<LogVO>> getByParam(HttpServletRequest request) {
        SysLog log = Request2ModelUtil.covert(SysLog.class, request);

        List<SysLog> list = sysLogService.list(new QueryWrapper<>(log));
        if (CollectionUtils.isEmpty(list))
            return successResponse();
        List<LogVO> voList = new ArrayList<>(list.size());
        list.forEach(po -> {
            LogVO vo = new LogVO();
            BeanUtils.copyProperties(log, po);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @GetMapping("/log/page/{current}/{size}")
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<IPage<LogVO>> getPage(@PathVariable(name = "current") int current,
                                              @PathVariable(name = "size") int size, HttpServletRequest request) {
        SysLog log = Request2ModelUtil.covert(SysLog.class, request);
        if (size == 0) size = 10;
        if (current == 0) current = 1;
        return successResponse(this.sysLogService.getPage(log, current, size));
    }
}
