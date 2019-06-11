package timing.ukulele.service.portal.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.TreeUtil;
import timing.ukulele.facade.portal.api.IDeptFacade;
import timing.ukulele.facade.portal.model.data.DeptTree;
import timing.ukulele.facade.portal.model.persistent.SysDept;
import timing.ukulele.service.portal.service.SysDeptService;
import timing.ukulele.web.controller.BaseController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public final class DeptController extends BaseController implements IDeptFacade {
    private final SysDeptService deptService;

    @Autowired
    public DeptController(SysDeptService deptService) {
        this.deptService = deptService;
    }

    @Override
    public ResponseData<SysDept> get(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.deptService.getById(id));
    }

    @Override
    public ResponseData<List<SysDept>> getDeptByParam(Map<String, Object> map) {
        if (map == null)
            return paraErrorResponse();
        List<SysDept> list = new ArrayList<>(this.deptService.listByMap(map));
        return successResponse(list);
    }

    @Override
    public ResponseData<List<DeptTree>> getDeptTree() {
        List<SysDept> list = this.deptService.list();
        return successResponse(createMenuTree(list));
    }

    @Override
    public ResponseData<Boolean> add(SysDept sysDept) {
        if (sysDept == null || sysDept.getId() != null)
            return paraErrorResponse();
        return successResponse(this.deptService.save(sysDept));
    }

    @Override
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return this.deptService.removeDept(id);
    }

    @Override
    public ResponseData<Boolean> edit(SysDept sysDept) {
        if (sysDept == null || sysDept.getId() == null)
            return paraErrorResponse();
        return successResponse(this.deptService.updateById(sysDept));
    }

    private List<DeptTree> createMenuTree(Collection<SysDept> menuList) {
        List<DeptTree> menuTreeList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuList))
            menuList.forEach(dept -> {
                DeptTree node = new DeptTree();
                BeanUtils.copyProperties(dept, node);
                menuTreeList.add(node);
            });
        return TreeUtil.buildByRecursive(menuTreeList, 0L);
    }
}
