package timing.ukulele.service.syslog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import timing.ukulele.facade.syslog.model.persistent.SysLog;

/**
 * <p>
 * 日志表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysLogMapper extends BaseMapper<SysLog> {

}
