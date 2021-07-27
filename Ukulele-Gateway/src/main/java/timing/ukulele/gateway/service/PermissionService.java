package timing.ukulele.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import timing.ukulele.common.constant.RoleConstant;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.portal.data.RolePermission;
import timing.ukulele.data.portal.view.MenuVO;
import timing.ukulele.facade.portal.IRoleFacade;
import timing.ukulele.facade.portal.feign.IMenuFeignFacade;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 请求权限判断service
 * @author fengxici
 */
@Slf4j
@Service("permissionService")
public class PermissionService {

    @SuppressWarnings("unchecked")
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        if (principal != null) {
            if (CollectionUtils.isEmpty(grantedAuthorityList)) {
                log.warn("角色列表为空：{}", authentication.getPrincipal());
                return false;
            }
            final boolean[] hasPermission = {false};
            //系统支持 admin,super,user三种类型的角色，含有任一个角色就放行
            for (SimpleGrantedAuthority item : grantedAuthorityList) {
                if (RoleConstant.USER.equalsIgnoreCase(item.getAuthority())
                        || RoleConstant.ADMIN.equalsIgnoreCase(item.getAuthority())
                        || RoleConstant.SUPER.equalsIgnoreCase(item.getAuthority())) {
                    hasPermission[0] = true;
                    break;
                }
            }
            return hasPermission[0];
        }
        log.warn("Principal为空！");
        return false;
    }
}