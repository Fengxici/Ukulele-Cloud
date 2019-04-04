package timing.ukulele.service.system.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import timing.ukulele.api.model.system.ModuleAndSystemModel;
import timing.ukulele.api.model.system.SystemModel;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.system.mapper.SystemMapper;

import java.util.List;

@DS("slave")
@Service
public class SystemService extends BaseService<SystemModel> {
    public List<ModuleAndSystemModel> selectModuleAndSystem() {
        return ((SystemMapper)baseMapper).selectModuleAndSystem();
    }
}
