package timing.ukulele.service.auth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import timing.ukulele.facade.portal.api.feign.IMenuFeignFacade;
import timing.ukulele.facade.portal.api.feign.IRoleFeignFacade;
import timing.ukulele.facade.portal.model.view.MenuVO;
import timing.ukulele.facade.portal.model.view.RoleVO;
import timing.ukulele.facade.user.api.feign.IUserFeignFacade;
import timing.ukulele.facade.user.model.view.UserVO;

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
