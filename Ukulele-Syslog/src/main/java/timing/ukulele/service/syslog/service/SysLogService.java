package timing.ukulele.service.syslog.service;

import org.springframework.stereotype.Service;
import timing.ukulele.facade.syslog.model.persistent.SysLog;
import timing.ukulele.persistence.service.BaseService;

import java.util.Date;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 */
@Service
public class SysLogService extends BaseService<SysLog> {

    public Boolean updateByLogId(Long id) {
        SysLog sysLog = new SysLog();
        sysLog.setId(id);
        sysLog.setEnable(Boolean.FALSE);
        sysLog.setUpdateTime(new Date());
        return updateById(sysLog);
    }
}
