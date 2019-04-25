package timing.ukulele.service.syslog.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.syslog.model.persistent.SysLog;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 */
@Service
public class SysLogService extends ServiceImpl<BaseMapper<SysLog>, SysLog> {

}
