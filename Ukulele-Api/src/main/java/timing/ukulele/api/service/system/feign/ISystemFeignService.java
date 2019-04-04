package timing.ukulele.api.service.system.feign;

import org.springframework.cloud.openfeign.FeignClient;
import timing.ukulele.api.model.system.GlobalUserModel;
import timing.ukulele.api.model.system.ModuleModel;
import timing.ukulele.api.model.system.OAuthClientDetailsModel;
import timing.ukulele.api.model.system.RoleModel;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.web.pojo.ResponseCode;
import timing.ukulele.api.service.system.ISystemService;

import java.util.List;

@FeignClient(name = "system-service", fallback = ISystemFeignService.HystrixClientFallback.class)
public interface ISystemFeignService extends ISystemService {
    class HystrixClientFallback implements ISystemFeignService {

        @Override
        public ResponseData<List<OAuthClientDetailsModel>> getAllClient() {
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }

        @Override
        public ResponseData<GlobalUserModel> getUserByUserName(String userName) {
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }

        @Override
        public ResponseData<GlobalUserModel> getUserByPhone(String phone) {
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }

        @Override
        public ResponseData<List<ModuleModel>> getMenusByUserId(long userId) {
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }

        @Override
        public ResponseData<List<RoleModel>> getRoleByUserId(long userId) {
            return new ResponseData<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMessage());
        }
    }
}
