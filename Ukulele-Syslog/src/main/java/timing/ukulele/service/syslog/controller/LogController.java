package timing.ukulele.service.syslog.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.common.exception.ClientException;
import timing.ukulele.facade.syslog.api.ILogFacade;
import timing.ukulele.facade.syslog.model.persistent.SysLog;
import timing.ukulele.service.syslog.service.SysLogService;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 日志
 * </p>
 */
@RestController
@RequestMapping("/log")
public final class LogController implements ILogFacade {

    private final SysLogService sysLogService;

    @Autowired
    public LogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @Override
    public ResponseData<SysLog> add(SysLog sysLog) {
        if (sysLogService.save(sysLog)) {
            return new ResponseData<>(ResponseCode.SUCCESS);
        }
        return new ResponseData<>(ResponseCode.ERROR);
    }

    @Override
    public ResponseData<Boolean> delete(Long aLong) {
        return null;
    }
}
