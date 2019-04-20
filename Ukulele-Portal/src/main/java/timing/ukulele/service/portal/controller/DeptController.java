package timing.ukulele.service.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.IDeptFacade;
import timing.ukulele.facade.portal.model.data.DeptTree;
import timing.ukulele.facade.portal.model.persistent.SysDept;
import timing.ukulele.service.portal.service.SysDeptService;
import timing.ukulele.web.controller.BaseController;

import java.util.ArrayList;
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
        List<SysDept> list = (ArrayList) this.deptService.listByMap(map);
        return successResponse(list);
    }

    @Override
    public ResponseData<List<DeptTree>> getDeptTree() {
        return null;
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
        return successResponse(this.deptService.removeById(id));
    }

    @Override
    public ResponseData<Boolean> edit(SysDept sysDept) {
        if (sysDept == null || sysDept.getId() == null)
            return paraErrorResponse();
        return successResponse(this.deptService.save(sysDept));
    }
}
