package timing.ukulele.service.portal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.data.portal.view.AntIconVO;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.AntIconMapper;
import timing.ukulele.service.portal.persistent.AntIcon;

import java.util.ArrayList;
import java.util.List;

@Service
public class AntIconService extends BaseService<AntIconMapper, AntIcon> {
    public IPage<AntIconVO> getPage(AntIcon icon, int current, int size) {
        Page<AntIcon> page = new Page<>(current, size);

        if (icon == null)
            icon = new AntIcon();
        IPage<AntIcon> iPage = this.getBaseMapper().selectPage(page, new QueryWrapper<>(icon));
        Page<AntIconVO> voPage = new Page<>(current, size);
        if (iPage != null && !CollectionUtils.isEmpty(iPage.getRecords())) {
            List<AntIconVO> voList = new ArrayList<>(iPage.getRecords().size());
            iPage.getRecords().forEach(po -> {
                AntIconVO vo = new AntIconVO();
                BeanUtils.copyProperties(po, vo);
                voList.add(vo);
            });
            voPage.setRecords(voList);
        }
        return voPage;
    }
}
