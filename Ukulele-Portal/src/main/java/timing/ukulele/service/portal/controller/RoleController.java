package timing.ukulele.service.portal.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.IRoleFacade;
import timing.ukulele.facade.portal.model.persistent.SysRole;
import timing.ukulele.facade.portal.model.view.RoleVO;
import timing.ukulele.service.portal.service.SysRoleService;
import timing.ukulele.web.controller.BaseController;
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public final class RoleController extends BaseController implements IRoleFacade {
    private final SysRoleService sysRoleService;

    @Autowired
    public RoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @Override
    public ResponseData<SysRole> role(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.sysRoleService.getById(id));
    }

    @Override
    public ResponseData<List<SysRole>> getRoleByParam(Map<String, Object> map) {
        return successResponse(new ArrayList<>(this.sysRoleService.listByMap(map)));
    }

    @Override
    public ResponseData<Boolean> role(RoleVO role) {
        if (role == null || role.getId() != null)
            return paraErrorResponse();
        SysRole roleData = new SysRole();
        BeanUtils.copyProperties(role, roleData);
        return successResponse(this.sysRoleService.save(roleData));
    }

    @Override
    public ResponseData<Boolean> roleUpdate(RoleVO role) {
        if (role == null || role.getId() == null)
            return paraErrorResponse();
        SysRole roleData = new SysRole();
        BeanUtils.copyProperties(role, roleData);
        return successResponse(this.sysRoleService.saveOrUpdate(roleData));
    }

    @Override
    public ResponseData<Boolean> roleDel(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.sysRoleService.removeById(id));
    }

    @Override
    public ResponseData<List<SysRole>> getRoleByUserId(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.sysRoleService.getRoleByUserId(id));
    }

    @Override
    public ResponseData<Boolean> deleteUserRole(Long userId, Long roleId) {
        if (userId == null || userId <= 0)
            return paraErrorResponse();
        return successResponse(this.sysRoleService.deleteUserRole(userId, roleId));
    }

    @Override
    public ResponseData<Boolean> addUserRole(Long userId, Long roleId) {
        if (userId == null || userId <= 0 || roleId == null || roleId <= 0)
            return paraErrorResponse();
        return successResponse(this.sysRoleService.addUserRole(userId, roleId));
    }

    @GetMapping("/page/{current}/{size}")
    public ResponseData<IPage<SysRole>> getPage(@PathVariable(name="current")int current,
                                                @PathVariable(name="size") int size, HttpServletRequest request) {
        RoleVO roleVO = Request2ModelUtil.covert(RoleVO.class, request);
        if(size==0)size=10;
        if(current==0)current=1;
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleVO,role);
        return successResponse(this.sysRoleService.getPage(role,current,size));
    }
}
