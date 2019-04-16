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
import timing.ukulele.facade.portal.api.feign.IMenuFeignService;
import timing.ukulele.facade.portal.model.view.MenuVO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求权限判断service
 */
@Slf4j
@Service("permissionService")
public class PermissionService {

    private final IMenuFeignService menuService;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    public PermissionService(IMenuFeignService menuService) {
        this.menuService = menuService;
    }

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 前端跨域OPTIONS请求预检放行 也可通过前端配置代理实现
        // 在这里放行具有一定风险,也可通过前端配置代理实现
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> grantedAuthorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        boolean hasPermission = false;

        if (principal != null) {
            if (CollectionUtils.isEmpty(grantedAuthorityList)) {
                log.warn("角色列表为空：{}", authentication.getPrincipal());
                return hasPermission;
            }

            List<MenuVO> urls = new ArrayList<>();
            for (SimpleGrantedAuthority authority : grantedAuthorityList) {
                if (!StringUtils.equals(authority.getAuthority(), "ROLE_USER")) {
                    // TODO 角色与菜单权限的关联关系需要缓存提高访问效率
                    urls = menuService.findMenuByRole(authority.getAuthority());
                    if (urls == null)
                        urls = new ArrayList<>();
                }
            }
            for (MenuVO menu : urls) {
                if (StringUtils.isNotEmpty(menu.getUrl()) && antPathMatcher.match(menu.getUrl(), request.getRequestURI())
                        && request.getMethod().equalsIgnoreCase(menu.getMethod())) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
