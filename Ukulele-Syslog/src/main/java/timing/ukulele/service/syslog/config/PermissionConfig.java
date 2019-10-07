package timing.ukulele.service.syslog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import timing.ukulele.web.interceptor.PermissionInterceptor;
import timing.ukulele.web.service.PermissionService;

/**
 * •权限拦截配置
 * •@className: PermissionConfig
 * •@author: 吕自聪
 * •@date: 2019/9/29
 */
@Configuration
public class PermissionConfig implements WebMvcConfigurer {
    private final PermissionService permissionService;

    @Autowired
    @Lazy
    public PermissionConfig(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor(permissionService));
    }
}
