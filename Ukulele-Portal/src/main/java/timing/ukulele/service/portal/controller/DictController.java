package timing.ukulele.service.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.IDictFacade;
import timing.ukulele.facade.portal.model.persistent.SysDict;
import timing.ukulele.facade.portal.model.persistent.SysDictIndex;
import timing.ukulele.service.portal.service.SysDictIndexService;
import timing.ukulele.service.portal.service.SysDictService;
import timing.ukulele.web.controller.BaseController;

import java.util.*;

@RestController
public final class DictController extends BaseController implements IDictFacade {
    private final SysDictService dictService;
    private final SysDictIndexService dictIndexService;

    @Autowired
    public DictController(SysDictService dictService, SysDictIndexService dictIndexService) {
        this.dictService = dictService;
        this.dictIndexService = dictIndexService;
    }

    @Override
    public ResponseData<SysDict> dict(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.dictService.getById(id));
    }

    @Override
    public ResponseData<List<SysDict>> findDictByIndex(String key) {
        if (StringUtils.isEmpty(key))
            return paraErrorResponse();
        SysDictIndex index = dictIndexService.getOne(new QueryWrapper<SysDictIndex>().eq("key_", key));
        if (index == null)
            failResponse();
        List<SysDict> list = this.dictService.list(new QueryWrapper<SysDict>().eq("index_id", index.getId()));
        return successResponse(list);
    }

    @Override
    public ResponseData<Boolean> dict(SysDict sysDict) {
        if (sysDict == null || sysDict.getId() != null)
            return paraErrorResponse();
        return successResponse(this.dictService.save(sysDict));
    }

    @Override
    public ResponseData<Boolean> deleteDict(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("index_id", id);
        Collection<SysDict> dicts = this.dictService.listByMap(map);
        if (dicts != null) {
            List<Long> ids = new ArrayList<>();
            dicts.forEach(dept -> ids.add(dept.getId()));
            if (ids.size() > 0)
                this.dictService.removeByIds(ids);
        }
        return successResponse(this.dictService.removeById(id));
    }

    @Override
    public ResponseData<Boolean> editDict(SysDict sysDict) {
        if (sysDict == null || sysDict.getId() == null)
            return paraErrorResponse();
        return successResponse(this.dictService.saveOrUpdate(sysDict));
    }

    @Override
    public ResponseData<SysDictIndex> dictIndex(Long id) {
        if (id == null || id < 0)
            return paraErrorResponse();
        return this.successResponse(this.dictIndexService.getById(id));
    }

    @Override
    public ResponseData<Boolean> dictIndex(SysDictIndex dictIndex) {
        if (dictIndex == null || dictIndex.getId() != null)
            return paraErrorResponse();
        return successResponse(this.dictIndexService.save(dictIndex));
    }

    @Override
    public ResponseData<Boolean> editDictIndex(SysDictIndex dictIndex) {
        if (dictIndex == null || dictIndex.getId() == null)
            return paraErrorResponse();
        return successResponse(this.dictIndexService.updateById(dictIndex));
    }

    @Override
    public ResponseData<Boolean> deleteDictIndex(Long id) {
        if (id == null || id < 0)
            return paraErrorResponse();
        return successResponse(this.dictIndexService.removeById(id));
    }
}
