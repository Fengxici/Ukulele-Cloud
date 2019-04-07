package timing.ukulele.service.auth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.api.feign.IPortalFeignService;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.persistent.SysRole;
import timing.ukulele.facade.user.api.feign.IUserFeignService;
import timing.ukulele.facade.user.model.persistent.SysUser;

@Service
public class QrUserDetailService extends BaseUserDetailService {

    public QrUserDetailService(IUserFeignService userService, IPortalFeignService portalService, RedisTemplate<String, SysRole> redisTemplate, RedisTemplate<String, SysMenu> resourcesTemplate) {
        super(userService, portalService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected SysUser getUser(String qrCode) {
        return null;
    }
}
