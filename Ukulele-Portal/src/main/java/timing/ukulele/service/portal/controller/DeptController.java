package timing.ukulele.service.portal.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.constant.AbilityConstant;
import timing.ukulele.common.constant.RoleConstant;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.TreeUtil;
import timing.ukulele.data.portal.data.DeptTree;
import timing.ukulele.data.portal.view.SysDeptVO;
import timing.ukulele.facade.portal.IDeptFacade;
import timing.ukulele.service.portal.persistent.SysDept;
import timing.ukulele.service.portal.service.SysDeptService;
import timing.ukulele.web.annotation.RequiredPermission;
import timing.ukulele.web.controller.BaseController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public final class DeptController extends BaseController implements IDeptFacade {
    private final String router = "/system/dept";
    private final SysDeptService deptService;

    @Autowired
    public DeptController(SysDeptService deptService) {
        this.deptService = deptService;
    }

    @Override
    public ResponseData<SysDeptVO> get(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        SysDept dept = this.deptService.getById(id);
        if (dept == null)
            return successResponse();
        SysDeptVO vo = new SysDeptVO();
        BeanUtils.copyProperties(dept, vo);
        return successResponse(vo);
    }

    @Override
    public ResponseData<List<SysDeptVO>> getDeptByParam(Map<String, Object> map) {
        if (map == null)
            return paraErrorResponse();
        List<SysDept> list = new ArrayList<>(this.deptService.listByMap(map));
        if (CollectionUtils.isEmpty(list))
            return successResponse();
        List<SysDeptVO> voList = new ArrayList<>(list.size());
        list.forEach(po -> {
            SysDeptVO vo = new SysDeptVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = router)
    public ResponseData<List<DeptTree>> getDeptTree() {
        List<SysDept> list = this.deptService.list();
        return successResponse(createMenuTree(list));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = router)
    public ResponseData<Boolean> add(SysDeptVO sysDept) {
        if (sysDept == null || sysDept.getId() != null)
            return paraErrorResponse();
        SysDept po = new SysDept();
        BeanUtils.copyProperties(sysDept, po);
        return successResponse(this.deptService.save(po));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.DELETE, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = router)
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return this.deptService.removeDept(id);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.EDIT, acl = {RoleConstant.SUPER, RoleConstant.ADMIN}, router = router)
    public ResponseData<Boolean> edit(SysDeptVO sysDept) {
        if (sysDept == null || sysDept.getId() == null)
            return paraErrorResponse();
        SysDept po = new SysDept();
        BeanUtils.copyProperties(sysDept, po);
        return successResponse(this.deptService.updateById(po));
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
