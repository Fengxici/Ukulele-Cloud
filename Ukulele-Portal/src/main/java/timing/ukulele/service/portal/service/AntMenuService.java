package timing.ukulele.service.portal.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.common.util.TreeUtil;
import timing.ukulele.facade.portal.model.data.RoleMenuTree;
import timing.ukulele.facade.portal.model.view.RoleMenuEditVO;
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

    public ResponseData<Boolean> editRoleMenu(Long roleId, List<RoleMenuEditVO> list) {
        if (CollectionUtils.isEmpty(list))
            return new ResponseData<>(ResponseCode.ERROR.getCode(), "菜单列表为空", Boolean.FALSE);
        //1,查询菜单列表
        List<AntMenu> menuList = this.list();
        if (CollectionUtils.isEmpty(menuList))
            return new ResponseData<>(ResponseCode.ERROR.getCode(), "系统尚无任何菜单", Boolean.FALSE);
        Map<Long, AntMenu> menuMap = new HashMap<>(menuList.size());
        menuList.forEach(item -> menuMap.put(item.getId(), item));
        //2.查询该角色已有的菜单
        List<AntRoleMenu> roleMenuList = this.roleMenuMapper.selectRoleMenu(roleId);
        Map<Long, RoleMenuEditVO> roleMenuMap = new HashMap<>(CollectionUtils.isEmpty(roleMenuList) ? 0 : roleMenuList.size());
        List<AntRoleMenu> toAddList = new ArrayList<>();
        List<AntRoleMenu> toDeleteList = new ArrayList<>();
        Set<Long> toDeleteSet = new HashSet<>();
        Set<Long> toAddSet = new HashSet<>();
        list.forEach(item -> {
            roleMenuMap.put(item.getMenuId(), item);
            //系统存在的菜单才处理
            if (null != menuMap.get(item.getMenuId())) {
                if (item.isDelete()) {
                    toDeleteSet.addAll(getNodeAndAllParent(item.getMenuId(), menuMap));
                } else {
                    toAddSet.addAll(getNodeAndAllParent(item.getMenuId(), menuMap));
                }
            }
        });
        // 如果删除和新增的存在交集，则程序无法处理
        Set<Long> result = new HashSet<>(toAddSet.size() > toDeleteSet.size() ? toAddSet : toDeleteSet);
        if (!CollectionUtils.isEmpty(toAddSet) && !CollectionUtils.isEmpty(toDeleteSet) && result.retainAll(toDeleteSet))
            return new ResponseData<>(ResponseCode.ERROR.getCode(), "要删除的和要新增的才能在交集，无法处理", Boolean.FALSE);
        if (!CollectionUtils.isEmpty(toAddSet)) {
            toAddSet.forEach(item -> {
                AntRoleMenu roleMenu = new AntRoleMenu();
                roleMenu.setMenuId(item);
                roleMenu.setRoleId(roleId);
                RoleMenuEditVO vo = roleMenuMap.get(item);
                if (null != vo && !CollectionUtils.isEmpty(vo.getAbilities()))
                    roleMenu.setAbility(Arrays.toString(vo.getAbilities().toArray()));
                toAddList.add(roleMenu);
            });
        }
        if (!CollectionUtils.isEmpty(toDeleteSet)) {
            toDeleteSet.forEach(item -> {
                AntRoleMenu roleMenu = new AntRoleMenu();
                roleMenu.setMenuId(item);
                roleMenu.setRoleId(roleId);
                toDeleteList.add(roleMenu);
            });
        }
        return new ResponseData<>(ResponseCode.SUCCESS, Boolean.TRUE);
    }

    public IPage<AntMenu> getPage(AntMenu antMenu, int current, int size) {
        Page<AntMenu> page = new Page<>(current, size);
        IPage<AntMenu> iPage = this.baseMapper.selectPage(page, new QueryWrapper<>(antMenu));
        return page.setRecords(iPage.getRecords());
    }

    public Set<Long> getNodeAndAllParent(Long menuId, Map<Long, AntMenu> menuMap) {
        Set<Long> set = new HashSet<>();
        AntMenu menu = menuMap.get(menuId);
        if (menu == null)
            return set;
        set.add(menuId);
        if (!menu.getParentId().equals(0L))
            set.addAll(getNodeAndAllParent(menu.getParentId(), menuMap));
        return set;
    }

}
