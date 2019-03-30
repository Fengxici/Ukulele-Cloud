package timing.ukulele.service.auth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import timing.ukulele.api.model.GlobalUserModel;
import timing.ukulele.api.model.ModuleModel;
import timing.ukulele.api.model.RoleModel;
import timing.ukulele.api.service.feign.ISystemFeignService;

@Service
public class QrUserDetailService extends BaseUserDetailService {

    public QrUserDetailService(ISystemFeignService systemService, RedisTemplate<String, RoleModel> redisTemplate, RedisTemplate<String, ModuleModel> resourcesTemplate) {
        super(systemService, redisTemplate, resourcesTemplate);
    }

    @Override
    protected GlobalUserModel getUser(String qrCode) {
        return null;
    }
}
