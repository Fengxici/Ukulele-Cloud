package timing.ukulele.service.portal.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import timing.ukulele.common.constant.AbilityConstant;
import timing.ukulele.common.constant.RoleConstant;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.portal.view.AntIconVO;
import timing.ukulele.facade.portal.IAntIconFacade;
import timing.ukulele.service.portal.persistent.AntIcon;
import timing.ukulele.service.portal.service.AntIconService;
import timing.ukulele.web.annotation.RequiredPermission;
import timing.ukulele.web.controller.BaseController;
import timing.ukulele.web.util.Request2ModelUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AntIconController extends BaseController implements IAntIconFacade {
    private final String router = "/config/icon";
    private final AntIconService antIconService;

    @Autowired
    public AntIconController(AntIconService antIconService) {
        this.antIconService = antIconService;
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<AntIconVO> get(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        AntIcon po = this.antIconService.getById(id);
        if (po != null) {
            AntIconVO vo = new AntIconVO();
            BeanUtils.copyProperties(po, vo);
            return successResponse(vo);
        }
        return successResponse();
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<List<AntIconVO>> getByParam(HttpServletRequest request) {
        AntIcon antIcon = Request2ModelUtil.covert(AntIcon.class, request);
        List<AntIcon> poList = this.antIconService.list(new QueryWrapper<>(antIcon));
        if (CollectionUtils.isEmpty(poList))
            return successResponse();
        List<AntIconVO> voList = new ArrayList<>(poList.size());
        poList.forEach(po -> {
            AntIconVO vo = new AntIconVO();
            BeanUtils.copyProperties(po, vo);
            voList.add(vo);
        });
        return successResponse(voList);
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.ADD, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<Boolean> add(AntIconVO sysAntIcon) {
        if (sysAntIcon == null || sysAntIcon.getId() != null)
            return paraErrorResponse();
        AntIcon icon = new AntIcon();
        BeanUtils.copyProperties(sysAntIcon, icon);
        return successResponse(this.antIconService.save(icon));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.DELETE, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<Boolean> delete(Long id) {
        if (id == null || id <= 0)
            return paraErrorResponse();
        return successResponse(this.antIconService.removeById(id));
    }

    @Override
    @RequiredPermission(ability = AbilityConstant.EDIT, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<Boolean> edit(AntIconVO sysAntIcon) {
        if (sysAntIcon == null || sysAntIcon.getId() == null)
            return paraErrorResponse();
        AntIcon icon = new AntIcon();
        BeanUtils.copyProperties(sysAntIcon, icon);
        return successResponse(this.antIconService.saveOrUpdate(icon));
    }

    @GetMapping("/page/{current}/{size}")
    @RequiredPermission(ability = AbilityConstant.QUERY, acl = {RoleConstant.SUPER}, router = this.router)
    public ResponseData<IPage<AntIconVO>> getPage(@PathVariable(name = "current") int current,
                                                  @PathVariable(name = "size") int size, HttpServletRequest request) {
        AntIcon antIcon = Request2ModelUtil.covert(AntIcon.class, request);
        if (size == 0) size = 10;
        if (current == 0) current = 1;
        return successResponse(this.antIconService.getPage(antIcon, current, size));
    }
}