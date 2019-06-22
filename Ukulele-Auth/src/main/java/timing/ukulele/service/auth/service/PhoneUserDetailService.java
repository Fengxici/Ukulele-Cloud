package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.feign.IMenuFeignFacade;
import timing.ukulele.facade.portal.api.feign.IRoleFeignFacade;
import timing.ukulele.facade.portal.model.view.MenuVO;
import timing.ukulele.facade.portal.model.view.RoleVO;
import timing.ukulele.facade.user.api.feign.IUserFeignFacade;
import timing.ukulele.facade.user.model.view.UserVO;

@Service
@Slf4j
public class PhoneUserDetailService extends BaseUserDetailService {


    public PhoneUserDetailService(IUserFeignFacade userService, IRoleFeignFacade roleService, IMenuFeignFacade
            menuService, RedisTemplate<String, RoleVO> redisTemplate, RedisTemplate<String, MenuVO> resourcesTemplate) {
        super(userService, roleService, menuService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected UserVO getUser(String phone) {
        // 手机验证码调用FeignClient根据电话号码查询用户
        ResponseData<UserVO> baseUserResponseData = userService.getUserByPhone(phone);
        if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
            log.error("找不到该用户，手机号码：" + phone);
            throw new UsernameNotFoundException("找不到该用户，手机号码：" + phone);
        }
        return baseUserResponseData.getData();
    }
}
