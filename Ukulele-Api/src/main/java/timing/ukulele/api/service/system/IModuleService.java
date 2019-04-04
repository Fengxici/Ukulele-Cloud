package timing.ukulele.api.service.system;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import timing.ukulele.api.model.system.ModuleModel;
import timing.ukulele.common.data.ResponseData;

import java.util.List;

public interface IModuleService {
    /**
     * 根据userId查询菜单
     * @param userId
     * @return
     */
    @RequestMapping(value = "/menu/user/{userId}", method = RequestMethod.GET)
    ResponseData<List<ModuleModel>> getMenusByUserId(@PathVariable("userId") long userId);

}
