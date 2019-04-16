package timing.ukulele.service.portal.controller;

import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.data.ResponseVO;
import timing.ukulele.facade.portal.api.IRoleService;
import timing.ukulele.facade.portal.model.data.RoleDTO;
import timing.ukulele.facade.portal.model.persistent.SysRole;
import timing.ukulele.service.portal.service.SysRoleService;

import java.util.List;
import java.util.Map;


@RestController
public final class RoleController implements IRoleService {
    private final SysRoleService sysRoleService;

    @Autowired
    public RoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @Override
    public SysRole role(Long aLong) {
        return null;
    }

    @Override
    public ResponseVO role(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public ResponseVO roleUpdate(RoleDTO roleDTO) {
        return null;
    }

    @Override
    public ResponseVO roleDel(Long aLong) {
        return null;
    }

    @Override
    public List<SysRole> roleList(Long aLong) {
        return null;
    }

    @Override
    public Page rolePage(Map<String, Object> map) {
        return null;
    }

    @Override
    public ResponseVO roleMenuUpd(Map map) {
        return null;
    }

    @Override
    public ResponseData<List<SysRole>> getRoleByUserId(Long aLong) {
        return null;
    }
}
