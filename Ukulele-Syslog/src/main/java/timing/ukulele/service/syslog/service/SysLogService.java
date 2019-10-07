package timing.ukulele.service.syslog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.data.syslog.view.LogVO;
import timing.ukulele.service.syslog.persistent.SysLog;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 */
@Service
public class SysLogService extends ServiceImpl<BaseMapper<SysLog>, SysLog> {
    public IPage<LogVO> getPage(SysLog log, int current, int pageSize) {
        Page<SysLog> page = new Page<>(current, pageSize);
        IPage<SysLog> iPage = this.baseMapper.selectPage(page, new QueryWrapper<>(log).orderByDesc("create_time"));
        IPage<LogVO> voPage = new Page<>(current, pageSize);
        if (iPage != null && !CollectionUtils.isEmpty(iPage.getRecords())) {
            List<LogVO> voList = new ArrayList<>(iPage.getRecords().size());
            iPage.getRecords().forEach(po -> {
                LogVO vo = new LogVO();
                BeanUtils.copyProperties(po, vo);
                voList.add(vo);
            });
            voPage.setRecords(voList);
        }
        return voPage;
    }
}
