package timing.ukulele.service.syslog;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import timing.ukulele.data.syslog.LogExchange;
import timing.ukulele.data.syslog.view.LogVO;
import timing.ukulele.service.syslog.persistent.SysLog;
import timing.ukulele.service.syslog.service.SysLogService;

/**
 * @author fengxici
 */
@Component
public class LogMessageReceiver {

    private final SysLogService sysLogService;

    @Autowired
    public LogMessageReceiver(SysLogService sysLogService) {
        this.sysLogService = sysLogService;
    }

    @RabbitHandler
    @RabbitListener(queues = LogExchange.SYS_LOG_ROUTING_KEY)
    public void log(LogVO sysLog) {
        SysLog logPO = new SysLog();
        BeanUtils.copyProperties(sysLog, logPO);
        sysLogService.save(logPO);
    }
}
