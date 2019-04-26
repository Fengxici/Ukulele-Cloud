package timing.ukulele.service.portal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.model.persistent.AntIcon;
import timing.ukulele.persistence.service.BaseService;

import java.util.Map;

@Service
public class AntIconService extends BaseService<AntIcon> {
    public IPage<AntIcon> getPage(Map<String, Object> map) {
        int pageSize = map.get("size") == null ? 10 : Integer.parseInt(map.get("size").toString());
        int current = map.get("current") == null ? 1 : Integer.parseInt(map.get("current").toString());
        Page<AntIcon> page = new Page<>(current, pageSize);
        AntIcon icon = new AntIcon();
        icon.setTheme(map.get("theme_") == null ? null : map.get("theme_").toString());
        icon.setType(map.get("type_") == null ? null : map.get("type_").toString());
        icon.setIconfont(map.get("value_") == null ? null : map.get("value_").toString());
        IPage<AntIcon> iPage=this.baseMapper.selectPage(page, new QueryWrapper<>(icon));
        return page.setRecords(iPage.getRecords());
    }
}
