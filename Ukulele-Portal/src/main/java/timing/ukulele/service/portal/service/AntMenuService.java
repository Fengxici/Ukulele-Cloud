package timing.ukulele.service.portal.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.TreeUtil;
import timing.ukulele.facade.portal.model.data.RoleMenuTree;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.AntMenuMapper;
import timing.ukulele.service.portal.mapper.AntRoleMenuMapper;
import timing.ukulele.service.portal.persistent.AntMenu;
import timing.ukulele.service.portal.persistent.AntRoleMenu;

import java.util.*;

@Service
public class AntMenuService extends BaseService<AntMenu> {
    private final AntRoleMenuMapper roleMenuMapper;

    @Autowired
    public AntMenuService(AntRoleMenuMapper roleMenuMapper) {
        this.roleMenuMapper = roleMenuMapper;
    }

    public List<AntMenu> findMenuByRoleName(String role) {
        return ((AntMenuMapper) this.baseMapper).findMenuByRoleName(role);
    }

    public Boolean deleteRoleMenu(Long roleId, Long menuId) {
        return roleMenuMapper.deleteRoleMenu(roleId, menuId) >= 0;
    }

    public List<RoleMenuTree> findAllMenuWithRole(Long roleId) {
        List<AntMenu> menuList = list();
        List<AntRoleMenu> roleMenuList = roleMenuMapper.selectRoleMenu(roleId);
        Map<Long, AntRoleMenu> roleMenuMap = new HashMap<>(CollectionUtils.isEmpty(roleMenuList) ? 0 : roleMenuList.size());
        if (!CollectionUtils.isEmpty(roleMenuList))
            roleMenuList.forEach(item -> roleMenuMap.put(item.getMenuId(), item));
        List<RoleMenuTree> menuTreeList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(menuList))
            menuList.forEach(menu -> {
                RoleMenuTree node = new RoleMenuTree();
                node.setId(menu.getId());
                node.setText(menu.getText());
                node.setParentId(menu.getParentId());
                AntRoleMenu roleMenu = roleMenuMap.get(menu.getId());
                if (roleMenu != null) {
                    node.setRoleId(roleId);
                    if (StringUtils.isNotEmpty(roleMenu.getAbility()))
                        node.setAbilities(JSON.parseArray(roleMenu.getAbility(), String.class));
                }
                menuTreeList.add(node);
            });
        return TreeUtil.buildByRecursive(menuTreeList, 0L);
    }

    public Boolean addRoleMenu(Long roleId, Long menuId) {
        return roleMenuMapper.addRoleMenu(roleId, menuId) > 0;
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
        roleMenuMapper.deleteRoleMenuByMenuId(id);
        removeById(id);
        return new ResponseData<>(ResponseCode.SUCCESS, Boolean.TRUE);
    }

    public IPage<AntMenu> getPage(AntMenu antMenu, int current, int size) {
        Page<AntMenu> page = new Page<>(current, size);
        IPage<AntMenu> iPage = this.baseMapper.selectPage(page, new QueryWrapper<>(antMenu));
        return page.setRecords(iPage.getRecords());
    }
}
