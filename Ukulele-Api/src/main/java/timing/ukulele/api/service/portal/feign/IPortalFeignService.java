package timing.ukulele.api.service.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import timing.ukulele.api.model.portal.SysLog;
import timing.ukulele.api.model.portal.SysMenu;
import timing.ukulele.api.service.portal.IPortalService;

import java.util.HashSet;
import java.util.Set;

@FeignClient(name = "portal-service", fallback = IPortalFeignService.HystrixClientFallback.class)
public interface IPortalFeignService extends IPortalService {
    class HystrixClientFallback implements IPortalFeignService {

        @Override
        public void add(SysLog log) {

        }

        @Override
        public Set<SysMenu> findMenuByRole(String role) {
            return new HashSet<>();
        }
    }
}
