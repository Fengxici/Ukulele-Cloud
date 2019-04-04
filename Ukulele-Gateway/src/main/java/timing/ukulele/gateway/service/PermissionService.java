package timing.ukulele.gateway.service;

import org.springframework.security.core.Authentication;
import javax.servlet.http.HttpServletRequest;

/**
 * 请求权限判断service
 */
public interface PermissionService {
    /**
     * 判断请求是否有权限
     *
     * @param request        HttpServletRequest
     * @param authentication 认证信息
     * @return 是否有权限
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
