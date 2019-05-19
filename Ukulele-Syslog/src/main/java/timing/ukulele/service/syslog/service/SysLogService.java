package timing.ukulele.service.syslog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public IPage<SysLog> getPage(SysLog log,int current,int pageSize) {
        Page<SysLog> page = new Page<>(current, pageSize);
        IPage<SysLog> iPage=this.baseMapper.selectPage(page, new QueryWrapper<>(log).orderByDesc("create_time"));
        return page.setRecords(iPage.getRecords());
    }
}
