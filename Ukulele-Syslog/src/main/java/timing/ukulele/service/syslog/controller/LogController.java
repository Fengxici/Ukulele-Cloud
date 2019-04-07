package timing.ukulele.service.syslog.controller;

import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.common.exception.ClientException;
import timing.ukulele.facade.syslog.api.ILogService;
import timing.ukulele.facade.syslog.model.persistent.SysLog;
import timing.ukulele.service.syslog.service.SysLogService;
import timing.ukulele.web.controller.CrudController;
import timing.ukulele.web.pojo.ResponseCode;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 日志表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/log")
public final class LogController implements ILogService {

    private final SysLogService sysLogService;

    @Autowired
    public LogController(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @Override
    public ResponseData<SysLog> add(SysLog sysLog) {
        if (sysLogService.save(sysLog)) {
            return new ResponseData<>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage());
        }
        return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
    }

    @Override
    public Page logPage(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseVO delete(Long aLong) {
        return null;
    }

    /**
     * 添加日志
     *
     * @param log    日志实体
     * @param result 错误信息
     */
    @PostMapping
    public void add(@Valid @RequestBody SysLog log, BindingResult result) {
        if (result.hasErrors()) {
            throw new ClientException(result.getAllErrors().get(0).getDefaultMessage());
        }
        sysLogService.save(log);
    }
}
