package timing.ukulele.service.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.constant.AbilityConstant;
import timing.ukulele.common.constant.RoleConstant;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.portal.view.DictIndexVO;
import timing.ukulele.data.portal.view.DictVO;
import timing.ukulele.facade.portal.IDictFacade;
import timing.ukulele.service.portal.persistent.SysDict;
import timing.ukulele.service.portal.persistent.SysDictIndex;
import timing.ukulele.service.portal.service.SysDictIndexService;
import timing.ukulele.service.portal.service.SysDictService;
import timing.ukulele.web.annotation.RequiredPermission;
import timing.ukulele.web.controller.BaseController;
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author fengxici
 */
@RestController
public final class DictController extends BaseController implements IDictFacade {
    private final String router = "/system/dict";
    private final SysDictService dictService;
    private final SysDictIndexService dictIndexService;

    @Autowired
    public DictController(SysDictService dictService, SysDictIndexService dictIndexService) {
        this.dictService = dictService;
        this.dictIndexService = dictIndexService;
    }

    @Override
    public ResponseData<DictVO> dict(Long id) {
        if (id == null || id <= 0) {
            return paraErrorResponse();
        }
        SysDict dict = this.dictService.getById(id);
        if (dict == null) {
            return successResponse();
        }
        DictVO vo = new DictVO();
        BeanUtils.copyProperties(dict, vo);
        return successResponse(vo);
    }

    @Override
    public ResponseData<List<DictVO>> findDictByIndex(String key) {
        if (StringUtils.isEmpty(key)) {
            return paraErrorResponse();
        }
        SysDictIndex index = dictIndexService.getOne(new QueryWrapper<SysDictIndex>().eq("key_", key));
        if (index == null) {
            return successResponse();
        }
        List<SysDict> list = this.dictService.list(new QueryWrapper<SysDict>().eq("index_id", index.getId()));
        if (CollectionUtils.isEmpty(list)) {
            return successResponse();
        }
        List<DictVO> voList = new ArrayList<>(list.size());
        list.forEach(po -> {
            DictVO vo = new DictVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER}, router = router)
    public ResponseData<Boolean> dict(DictVO sysDict) {
        if (sysDict == null || sysDict.getId() != null) {
            return paraErrorResponse();
        }
        SysDict dict = new SysDict();
        BeanUtils.copyProperties(sysDict, dict);
        return successResponse(this.dictService.save(dict));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.DELETE, acl = {RoleConstant.SUPER}, router = router)
    public ResponseData<Boolean> deleteDict(Long id) {
        if (id == null || id <= 0) {
            return paraErrorResponse();
        }
        Map<String, Object> map = new HashMap<>(2);
        map.put("index_id", id);
        Collection<SysDict> dicts = this.dictService.listByMap(map);
        if (dicts != null) {
            List<Long> ids = new ArrayList<>();
            dicts.forEach(dept -> ids.add(dept.getId()));
            if (ids.size() > 0) {
                this.dictService.removeByIds(ids);
            }
        }
        return successResponse(this.dictService.removeById(id));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.EDIT, acl = {RoleConstant.SUPER}, router = router)
    public ResponseData<Boolean> editDict(DictVO sysDict) {
        if (sysDict == null || sysDict.getId() == null) {
            return paraErrorResponse();
        }
        SysDict dict = new SysDict();
        BeanUtils.copyProperties(sysDict, dict);
        return successResponse(this.dictService.saveOrUpdate(dict));
    }

    @Override
    public ResponseData<DictIndexVO> dictIndex(Long id) {
        if (id == null || id < 0) {
            return paraErrorResponse();
        }
        SysDictIndex dictIndex = this.dictIndexService.getById(id);
        if (dictIndex == null) {
            return this.successResponse();
        }
        DictIndexVO vo = new DictIndexVO();
        BeanUtils.copyProperties(dictIndex, vo);
        return successResponse(vo);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER}, router = router)
    public ResponseData<Boolean> dictIndex(DictIndexVO dictIndex) {
        if (dictIndex == null || dictIndex.getId() != null) {
            return paraErrorResponse();
        }
        SysDictIndex po = new SysDictIndex();
        BeanUtils.copyProperties(dictIndex, po);
        return successResponse(this.dictIndexService.save(po));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.EDIT, acl = {RoleConstant.SUPER}, router = router)
    public ResponseData<Boolean> editDictIndex(DictIndexVO dictIndex) {
        if (dictIndex == null || dictIndex.getId() == null) {
            return paraErrorResponse();
        }
        SysDictIndex po = new SysDictIndex();
        BeanUtils.copyProperties(dictIndex, po);
        return successResponse(this.dictIndexService.updateById(po));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.DELETE, acl = {RoleConstant.SUPER}, router = router)
    public ResponseData<Boolean> deleteDictIndex(Long id) {
        if (id == null || id < 0) {
            return paraErrorResponse();
        }
        return successResponse(this.dictIndexService.removeById(id));
    }

    @GetMapping("/page/{current}/{size}")
    public ResponseData<IPage<DictIndexVO>> page(
            @PathVariable("current") int current,
            @PathVariable("size") int size,
            HttpServletRequest request) {
        if (current <= 0) {
            current = 1;
        }
        if (size <= 0) {
            size = 10;
        }
        DictIndexVO dictIndexVO = Request2ModelUtil.covert(DictIndexVO.class, request);
        if (dictIndexVO == null) {
            return paraErrorResponse();
        }
        IPage<DictIndexVO> resultPage = new Page<>(current, size);
        SysDictIndex dictIndex = new SysDictIndex();
        BeanUtils.copyProperties(dictIndexVO, dictIndex);
        IPage<SysDictIndex> page = this.dictIndexService.getPage(dictIndex, current, size);
        if (page != null && !CollectionUtils.isEmpty(page.getRecords())) {
            resultPage.setTotal(page.getTotal());
            resultPage.setPages(page.getPages());
            resultPage.setRecords(new ArrayList<>());
            for (SysDictIndex index : page.getRecords()) {
                DictIndexVO indexVO = new DictIndexVO();
                BeanUtils.copyProperties(index, indexVO);
                resultPage.getRecords().add(indexVO);
                List<DictVO> voList = new ArrayList<>();
                indexVO.setDictList(voList);
                List<SysDict> list = this.dictService.list(new QueryWrapper<SysDict>().eq("index_id", index.getId()).orderByAsc("sort_"));
                if (!CollectionUtils.isEmpty(list)) {
                    for (SysDict dict : list) {
                        DictVO vo = new DictVO();
                        BeanUtils.copyProperties(dict, vo);
                        voList.add(vo);
                    }
                }
            }
        }
        return successResponse(resultPage);
    }
}
