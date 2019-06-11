package timing.ukulele.service.portal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.model.persistent.AntMenu;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.AntMenuMapper;

import java.util.*;

@Service
public class AntMenuService extends BaseService<AntMenu> {
    public List<AntMenu> findMenuByRoleName(String role) {
        return ((AntMenuMapper) this.baseMapper).findMenuByRoleName(role);
    }

    public Boolean deleteRoleMenu(Long roleId, Long menuId) {
        return ((AntMenuMapper) this.baseMapper).deleteRoleMenu(roleId, menuId) >= 0;
    }

    public Boolean addRoleMenu(Long roleId, Long menuId) {
        return ((AntMenuMapper) this.baseMapper).addRoleMenu(roleId, menuId) > 0;
    }

    public List<AntMenu> getMenuByUserId(Long userId) {
        return ((AntMenuMapper) this.baseMapper).getMenuByUserId(userId);
    }

    public ResponseData<Boolean> removeMenu(Long id) {
        Map<String, Object> filter = new HashMap<>();
        filter.put("parent_id", id);
        if (!CollectionUtils.isEmpty(listByMap(filter))) {
            return new ResponseData<>(ResponseCode.BUSINESS_ERROR.getCode(), "请先删除子项", Boolean.FALSE);
        }
        ((AntMenuMapper) this.baseMapper).deleteRoleMenuByMenuId(id);
        removeById(id);
        return new ResponseData<>(ResponseCode.SUCCESS, Boolean.TRUE);
    }

    public IPage<AntMenu> getPage(AntMenu antMenu, int current, int size) {
        Page<AntMenu> page = new Page<>(current, size);
        IPage<AntMenu> iPage = this.baseMapper.selectPage(page, new QueryWrapper<>(antMenu));
        return page.setRecords(iPage.getRecords());
    }
}
