package timing.ukulele.service.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.portal.data.RolePermission;
import timing.ukulele.data.portal.view.RoleVO;
import timing.ukulele.facade.portal.IRoleFacade;
import timing.ukulele.service.portal.persistent.SysRole;
import timing.ukulele.service.portal.service.SysRoleService;
import timing.ukulele.web.controller.BaseController;
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
public final class RoleController extends BaseController implements IRoleFacade {
    private final SysRoleService roleService;

    @Autowired
    public RoleController(SysRoleService sysRoleService) {
        this.roleService = sysRoleService;
    }

    @Override
    public ResponseData<RoleVO> role(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        SysRole po = this.roleService.getById(id);
        if (po == null)
            return successResponse();
        RoleVO vo = new RoleVO();
        BeanUtils.copyProperties(po, vo);
        return successResponse(vo);
    }

    @Override
    public ResponseData<List<RoleVO>> getRoleByParam(Map<String, Object> map) {
        Collection<SysRole> poList = this.roleService.listByMap(map);
        if (CollectionUtils.isEmpty(poList))
            return successResponse();
        List<RoleVO> voList = new ArrayList<>(poList.size());
        poList.forEach(po -> {
            RoleVO vo = new RoleVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    public ResponseData<Boolean> role(RoleVO role) {
        if (role == null || role.getId() != null)
            return paraErrorResponse();
        SysRole roleData = new SysRole();
        BeanUtils.copyProperties(role, roleData);
        return successResponse(this.roleService.save(roleData));
    }

    @Override
    public ResponseData<Boolean> roleUpdate(RoleVO role) {
        if (role == null || role.getId() == null)
            return paraErrorResponse();
        SysRole roleData = new SysRole();
        BeanUtils.copyProperties(role, roleData);
        return successResponse(this.roleService.saveOrUpdate(roleData));
    }

    @Override
    public ResponseData<Boolean> roleDel(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.roleService.removeById(id));
    }

    @Override
    public ResponseData<List<RoleVO>> getRoleByUserId(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        List<SysRole> poList = this.roleService.getRoleByUserId(id);
        if (CollectionUtils.isEmpty(poList))
            return successResponse();
        List<RoleVO> voList = new ArrayList<>(poList.size());
        poList.forEach(po -> {
            RoleVO vo = new RoleVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    public ResponseData<Boolean> deleteUserRole(Long userId, Long roleId) {
        if (userId == null || userId <= 0)
            return paraErrorResponse();
        return successResponse(this.roleService.deleteUserRole(userId, roleId));
    }

    @Override
    public ResponseData<Boolean> addUserRole(Long userId, Long roleId) {
        if (userId == null || userId <= 0 || roleId == null || roleId <= 0)
            return paraErrorResponse();
        return successResponse(this.roleService.addUserRole(userId, roleId));
    }

    @GetMapping("/page/{current}/{size}")
    public ResponseData<IPage<RoleVO>> getPage(@PathVariable(name = "current") int current,
                                                @PathVariable(name = "size") int size, HttpServletRequest request) {
        RoleVO roleVO = Request2ModelUtil.covert(RoleVO.class, request);
        if (size == 0) size = 10;
        if (current == 0) current = 1;
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleVO, role);
        return successResponse(this.roleService.getPage(role, current, size));
    }

    @Override
    public ResponseData<Map<String, Map<String, RolePermission>>> rolePermission(List<String> roleCode) {
        return successResponse(roleService.rolePermission(roleCode));
    }
}
