package timing.ukulele.service.auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class PhoneUserDetailService extends BaseUserDetailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public PhoneUserDetailService(ISystemFeignService systemService, RedisTemplate<String, RoleModel> redisTemplate, RedisTemplate<String, ModuleModel> resourcesTemplate) {
        super(systemService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected GlobalUserModel getUser(String phone) {
        // 手机验证码调用FeignClient根据电话号码查询用户
        ResponseData<GlobalUserModel> baseUserResponseData = systemService.getUserByPhone(phone);
        if(baseUserResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseUserResponseData.getCode())){
            logger.error("找不到该用户，手机号码：" + phone);
            throw new UsernameNotFoundException("找不到该用户，手机号码：" + phone);
        }
        return baseUserResponseData.getData();
    }
}
