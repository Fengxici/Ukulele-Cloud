package timing.ukulele.service.auth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.api.feign.IMenuFeignFacade;
import timing.ukulele.facade.portal.api.feign.IRoleFeignFacade;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.persistent.SysRole;
import timing.ukulele.facade.user.api.feign.IUserFeignFacade;
import timing.ukulele.facade.user.model.persistent.SysUser;

@Service
public class QrUserDetailService extends BaseUserDetailService {

    public QrUserDetailService(IUserFeignFacade userService, IRoleFeignFacade roleService, IMenuFeignFacade
            menuService, RedisTemplate<String, SysRole> redisTemplate, RedisTemplate<String, SysMenu> resourcesTemplate) {
        super(userService, roleService, menuService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected SysUser getUser(String qrCode) {
        return null;
    }
}
