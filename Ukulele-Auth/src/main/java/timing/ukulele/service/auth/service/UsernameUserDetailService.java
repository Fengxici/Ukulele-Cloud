package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.portal.view.MenuVO;
import timing.ukulele.data.portal.view.RoleVO;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.portal.feign.IMenuFeignFacade;
import timing.ukulele.facade.portal.feign.IRoleFeignFacade;
import timing.ukulele.facade.user.feign.IUserFeignFacade;

@Service
@Slf4j
public class UsernameUserDetailService extends BaseUserDetailService {

    public UsernameUserDetailService(IUserFeignFacade userService, IRoleFeignFacade roleService, IMenuFeignFacade
            menuService, RedisTemplate<String, RoleVO> redisTemplate, RedisTemplate<String, MenuVO> resourcesTemplate) {
        super(userService, roleService, menuService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected UserVO getUser(String username) {
        // 账号密码登陆调用FeignClient根据用户名查询用户
        ResponseData<UserVO> baseUserResponseData = userService.getUserByUserName(username);
        if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
            log.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
        }
        return baseUserResponseData.getData();
    }
}
