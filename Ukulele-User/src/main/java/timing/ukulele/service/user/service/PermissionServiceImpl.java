package timing.ukulele.service.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.portal.data.RolePermission;
import timing.ukulele.facade.portal.IRoleFacade;
import timing.ukulele.web.service.PermissionService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * •角色权限服务实现
 * •@className: PermissionServiceImpl
 * •@author: 吕自聪
 * •@date: 2019/9/29
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    private final IRoleFacade roleService;

    @Autowired
    public PermissionServiceImpl(IRoleFacade roleService) {
        this.roleService = roleService;
    }

    @Override
    public Set<String> abilitySet(String router, List<String> acl) {
        if (StringUtils.isEmpty(router) || CollectionUtils.isEmpty(acl))
            return null;
        Set<String> abilitySet = new HashSet<>();
        ResponseData<Map<String, Map<String, RolePermission>>> responseData = roleService.rolePermission(acl);
        if (responseData == null || responseData.getData() == null)
            return null;
        Map<String, Map<String, RolePermission>> roleMenuPermission = responseData.getData();
        for (String item : acl) {
            Map<String, RolePermission> rolePermission = roleMenuPermission.get(item);
            if (CollectionUtils.isEmpty(rolePermission))
                continue;
            RolePermission permission = rolePermission.get(router);
            if (null == permission)
                continue;
            if (CollectionUtils.isEmpty(permission.getAbility()))
                continue;
            abilitySet.addAll(permission.getAbility());
        }
        return abilitySet;
    }
}
