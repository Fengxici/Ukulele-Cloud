package timing.ukulele.api.service.portal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import timing.ukulele.api.model.portal.SysLog;

public interface ILogService {
    /**
     * 添加日志
     *
     * @param log 日志实体
     */
    @PostMapping("/log")
    void add(@RequestBody SysLog log);
}
