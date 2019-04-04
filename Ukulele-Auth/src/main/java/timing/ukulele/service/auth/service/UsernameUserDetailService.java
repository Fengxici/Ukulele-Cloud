package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import timing.ukulele.api.model.system.GlobalUserModel;
import timing.ukulele.api.model.system.ModuleModel;
import timing.ukulele.api.model.system.RoleModel;
import timing.ukulele.api.service.system.feign.ISystemFeignService;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.web.pojo.ResponseCode;

@Service
@Slf4j
public class UsernameUserDetailService extends BaseUserDetailService {

    public UsernameUserDetailService(ISystemFeignService systemService, RedisTemplate<String, RoleModel> redisTemplate, RedisTemplate<String, ModuleModel> resourcesTemplate) {
        super(systemService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected GlobalUserModel getUser(String username) {
        // 账号密码登陆调用FeignClient根据用户名查询用户
        ResponseData<GlobalUserModel> baseUserResponseData = systemService.getUserByUserName(username);
        if (baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())) {
            log.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
        }
        return baseUserResponseData.getData();
    }
}
