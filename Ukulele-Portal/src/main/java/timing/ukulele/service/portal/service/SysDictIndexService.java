package timing.ukulele.service.portal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.persistent.SysDictIndex;

@Service
public class SysDictIndexService extends BaseService<SysDictIndex> {
    public IPage<SysDictIndex> getPage(SysDictIndex dictIndex, int current, int size) {
        Page<SysDictIndex> page = new Page<>(current, size);
        IPage<SysDictIndex> iPage = this.baseMapper.selectPage(page, new QueryWrapper<>(dictIndex));
        return page.setRecords(iPage.getRecords());
    }
}
