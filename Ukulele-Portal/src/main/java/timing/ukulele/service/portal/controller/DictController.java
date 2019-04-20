package timing.ukulele.service.portal.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.IDictFacade;
import timing.ukulele.facade.portal.model.persistent.SysDict;
import timing.ukulele.service.portal.service.SysDictService;
import timing.ukulele.web.controller.BaseController;

import java.util.*;

@RestController
public final class DictController extends BaseController implements IDictFacade {
    private final SysDictService dictService;

    @Autowired
    public DictController(SysDictService dictService) {
        this.dictService = dictService;
    }

    @Override
    public ResponseData<SysDict> dict(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.dictService.getById(id));
    }

    @Override
    public ResponseData<List<SysDict>> findDictByType(String type) {
        if (StringUtils.isEmpty(type))
            return paraErrorResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("type_", type);
        return successResponse((ArrayList) this.dictService.listByMap(map));

    }

    @Override
    public ResponseData<Boolean> dict(SysDict sysDict) {
        if (sysDict == null || sysDict.getId() != null)
            return paraErrorResponse();
        return successResponse(this.dictService.save(sysDict));
    }

    @Override
    public ResponseData<Boolean> deleteDict(Long id, String type) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        if (StringUtils.isEmpty(type))
            return successResponse(this.dictService.removeById(id));
        else {
            Map<String, Object> map = new HashMap<>();
            map.put("type_", type);
            Collection<SysDict> dicts = this.dictService.listByMap(map);
            if (dicts != null) {
                List<Long> ids = new ArrayList<>();
                dicts.forEach(dept -> ids.add(dept.getId()));
                if (ids.size() > 0)
                    return successResponse(this.dictService.removeByIds(ids));
            }
            return failResponse();
        }
    }

    @Override
    public ResponseData<Boolean> editDict(SysDict sysDict) {
        if (sysDict == null || sysDict.getId() == null)
            return paraErrorResponse();
        return successResponse(this.dictService.saveOrUpdate(sysDict));
    }
}
