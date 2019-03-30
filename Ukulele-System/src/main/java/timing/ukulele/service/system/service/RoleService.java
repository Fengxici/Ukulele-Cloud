package timing.ukulele.service.system.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import timing.ukulele.api.model.RoleModel;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.system.mapper.RoleMapper;

import java.util.List;

@DS("slave")
@Service
public class RoleService extends BaseService<RoleModel> {


    /**
     * 根据用户查询角色
     * @param userId
     * @return
     */
    public List<RoleModel> getRoleByUserId(long userId) {
        return ((RoleMapper)baseMapper).getRoleByUserId(userId);
    }


}
