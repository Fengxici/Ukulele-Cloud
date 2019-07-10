package timing.ukulele.service.auth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import timing.ukulele.data.portal.view.MenuVO;
import timing.ukulele.data.portal.view.RoleVO;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.portal.feign.IMenuFeignFacade;
import timing.ukulele.facade.portal.feign.IRoleFeignFacade;
import timing.ukulele.facade.user.feign.IUserFeignFacade;

@Service
public class QrUserDetailService extends BaseUserDetailService {

    public QrUserDetailService(IUserFeignFacade userService, IRoleFeignFacade roleService, IMenuFeignFacade
            menuService, RedisTemplate<String, RoleVO> redisTemplate, RedisTemplate<String, MenuVO> resourcesTemplate) {
        super(userService, roleService, menuService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected UserVO getUser(String qrCode) {
        return null;
    }
}
