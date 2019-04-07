package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PhoneUserDetailService extends BaseUserDetailService {


    public PhoneUserDetailService(IUserFeignService userService, IPortalFeignService systemService, RedisTemplate<String, SysRole> redisTemplate, RedisTemplate<String, SysMenu> resourcesTemplate) {
        super(userService, systemService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected SysUser getUser(String phone) {
        // 手机验证码调用FeignClient根据电话号码查询用户
        ResponseData<SysUser> baseUserResponseData = userService.getUserByPhone(phone);
        if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
            log.error("找不到该用户，手机号码：" + phone);
            throw new UsernameNotFoundException("找不到该用户，手机号码：" + phone);
        }
        return baseUserResponseData.getData();
    }
}
