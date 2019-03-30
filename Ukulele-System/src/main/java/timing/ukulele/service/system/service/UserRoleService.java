package timing.ukulele.service.system.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import timing.ukulele.api.model.UserRoleModel;
import timing.ukulele.persistence.service.BaseService;

import java.util.List;


@DS("slave")
@Service
public class UserRoleService extends BaseService<UserRoleModel> {

    /**
     * 保存用户角色
     *
     * @param baseUserRoleList
     */
    @DS("master")
    @Transactional
    public void saveUserRole(List<UserRoleModel> baseUserRoleList) {
        if (baseUserRoleList.size() > 0 && !StringUtils.isEmpty(baseUserRoleList.get(0).getRoleId())) {
            saveBatch(baseUserRoleList, baseUserRoleList.size());
        }
    }
}
