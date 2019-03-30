package timing.ukulele.service.system.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import timing.ukulele.api.model.RoleModuleModel;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.system.mapper.RoleModuleMapper;

import java.util.List;


@DS("slave")
@Service
public class RoleModuleService extends BaseService<RoleModuleModel> {

    @DS("master")
    @Transactional
    public void saveRoleModule(List<RoleModuleModel> roleModule) {
        if (roleModule.size() > 0 && roleModule.get(0).getRoleId()>0) {
            saveBatch(roleModule,roleModule.size());
        }
    }

    // 查询关联角色的叶子模块
    public List<RoleModuleModel> selectLeafRoleModule(String roleId) {
        return ((RoleModuleMapper)baseMapper).selectLeafRoleModule(roleId);
    }
}
