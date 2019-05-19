package timing.ukulele.service.portal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.model.persistent.AntIcon;
import timing.ukulele.persistence.service.BaseService;

@Service
public class AntIconService extends BaseService<AntIcon> {
    public IPage<AntIcon> getPage(AntIcon icon,int current,int size) {
        Page<AntIcon> page = new Page<>(current, size);
        IPage<AntIcon> iPage=this.baseMapper.selectPage(page, new QueryWrapper<>(icon));
        return page.setRecords(iPage.getRecords());
    }
}
