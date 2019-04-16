package timing.ukulele.service.auth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.api.feign.IMenuFeignService;
import timing.ukulele.facade.portal.api.feign.IRoleFeignService;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.persistent.SysRole;
import timing.ukulele.facade.user.api.feign.IUserFeignService;
import timing.ukulele.facade.user.model.persistent.SysUser;

@Service
public class QrUserDetailService extends BaseUserDetailService {

    public QrUserDetailService(IUserFeignService userService, IRoleFeignService roleService, IMenuFeignService menuService, RedisTemplate<String, SysRole> redisTemplate, RedisTemplate<String, SysMenu> resourcesTemplate) {
        super(userService, roleService, menuService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected SysUser getUser(String qrCode) {
        return null;
    }
}
