package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.feign.IPortalFeignService;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.persistent.SysRole;
import timing.ukulele.facade.user.api.feign.IUserFeignService;
import timing.ukulele.facade.user.model.persistent.SysUser;
import timing.ukulele.web.pojo.ResponseCode;

@Service
@Slf4j
public class UsernameUserDetailService extends BaseUserDetailService {

    public UsernameUserDetailService(IUserFeignService userService, IPortalFeignService portalService, RedisTemplate<String, SysRole> redisTemplate, RedisTemplate<String, SysMenu> resourcesTemplate) {
        super(userService, portalService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected SysUser getUser(String username) {
        // 账号密码登陆调用FeignClient根据用户名查询用户
        ResponseData<SysUser> baseUserResponseData = userService.getUserByUserName(username);
        if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
            log.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
        }
        return baseUserResponseData.getData();
    }
}
