package timing.ukulele.api.service.system;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import timing.ukulele.api.model.system.RoleModel;
import timing.ukulele.common.data.ResponseData;

import java.util.List;

public interface IRoleService {
    /**
     * 根据userId查询角色
     * @param userId
     * @return
     */
    @RequestMapping(value = "/role/user/{userId}", method = RequestMethod.GET)
    ResponseData<List<RoleModel>> getRoleByUserId(@PathVariable("userId") long userId);

}
