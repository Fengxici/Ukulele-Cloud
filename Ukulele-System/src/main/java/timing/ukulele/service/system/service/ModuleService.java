package timing.ukulele.service.system.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import timing.ukulele.api.model.system.ModuleModel;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.system.mapper.ModuleMapper;

import java.util.List;

@DS("slave")
@Service
public class ModuleService extends BaseService<ModuleModel> {
    /**
     * 根据用户查询菜单
     *
     * @param userId
     * @return
     */
    public List<ModuleModel> getMenusByUserId(long userId) {
        return ((ModuleMapper) baseMapper).getMenusByUserId(userId);
    }

    public List<ModuleModel> getModuleTree(long id, long systemId) {
        return ((ModuleMapper) baseMapper).selectModuleTree(id, systemId);
    }
}
