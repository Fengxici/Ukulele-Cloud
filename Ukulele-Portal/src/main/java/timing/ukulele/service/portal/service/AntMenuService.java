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
import timing.ukulele.data.portal.data.RoleMenuTree;
import timing.ukulele.data.portal.view.AntRoleMenuEditVO;
import timing.ukulele.data.portal.view.RoleMenuEditVO;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.AntMenuMapper;
import timing.ukulele.service.portal.mapper.AntRoleMenuMapper;
import timing.ukulele.service.portal.mapper.SysRoleMapper;
import timing.ukulele.service.portal.persistent.AntMenu;
import timing.ukulele.service.portal.persistent.AntRoleMenu;
import timing.ukulele.service.portal.persistent.SysRole;
import timing.ukulele.service.portal.persistent.MenuPermission;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AntMenuService extends BaseService<AntMenu> {
    private final AntRoleMenuMapper roleMenuMapper;
    private final SysRoleMapper roleMapper;

    @Autowired
    public AntMenuService(AntRoleMenuMapper roleMenuMapper, SysRoleMapper roleMapper) {
        this.roleMenuMapper = roleMenuMapper;
        this.roleMapper = roleMapper;
    }
    
    public List<MenuPermission> findRoleMenuPermission(String role) {
        return ((AntMenuMapper) this.baseMapper).findRoleMenuAbility(role);
    }
    
    public List<AntMenu> findMenuByRoleName(String role) {
        return ((AntMenuMapper) this.baseMapper).findMenuByRoleName(role);
    }

    public Boolean deleteRoleMenu(Long roleId, Long menuId) {
        return roleMenuMapper.deleteRoleMenu(roleId, menuId) >= 0;
    }

    public List<RoleMenuTree> findAllMenuWithRole(Long roleId) {
        List<AntMenu> menuList = list();
        if (CollectionUtils.isEmpty(menuList))
            return null;
        List<AntRoleMenu> roleMenuList = roleMenuMapper.selectRoleMenu(roleId);
        Map<Long, AntRoleMenu> roleMenuMap = new HashMap<>(CollectionUtils.isEmpty(roleMenuList) ? 0 : roleMenuList.size());
        if (!CollectionUtils.isEmpty(roleMenuList))
            roleMenuList.forEach(item -> roleMenuMap.put(item.getMenuId(), item));
        //父节点集合
        Set<Long> parentSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(menuList)) {
            menuList.forEach(menu -> {
                if (!parentSet.contains(menu.getParentId()))
                    parentSet.add(menu.getParentId());
            });
        }
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
                    //叶子节点才设置选中状态，ng-alain前端会判断父节点是否要选中
                    node.setChecked(!parentSet.contains(menu.getId()));
                    if (StringUtils.isNotEmpty(roleMenu.getAbility()))
                        node.setAbilities(JSON.parseArray(roleMenu.getAbility(), String.class));
                } else {
                    node.setChecked(false);
                }
                menuTreeList.add(node);
            });
        return TreeUtil.buildByRecursive(menuTreeList, 0L);
    }


    public HashSet<String> findUserPageAbilities(Long userId, String router) {
        List<AntMenu> menuList = list();
        if (CollectionUtils.isEmpty(menuList))
            return null;
        List<AntMenu> routerMenu = menuList.stream().filter(antMenu -> router.equalsIgnoreCase(antMenu.getLink())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(routerMenu))
            return null;
        Set<Long> routerSet = routerMenu.stream().map(AntMenu::getId).collect(Collectors.toSet());
        List<SysRole> roleList = roleMapper.getRoleByUserId(userId);
        if (CollectionUtils.isEmpty(roleList))
            return null;
        List<String> abilityList = new ArrayList<>();
        roleList.forEach(item -> {
            List<AntRoleMenu> roleMenuList = roleMenuMapper.selectRoleMenu(item.getId());
            if (!CollectionUtils.isEmpty(roleMenuList)) {
                roleMenuList.forEach(ability -> {
                    if (routerSet.contains(ability.getMenuId())) {
                        if (StringUtils.isNotEmpty(ability.getAbility())) {
                            List<String> tmp = JSON.parseArray(ability.getAbility(), String.class);
                            abilityList.addAll(tmp);
                        }
                    }
                });
            }
        });
        return new HashSet<>(abilityList);
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

    public ResponseData<Boolean> editRoleMenu(AntRoleMenuEditVO vo) {
        if (null == vo || vo.getRoleId() == null)
            return new ResponseData<>(ResponseCode.ERROR.getCode(), "菜单列表为空", Boolean.FALSE);
        //列表为空则是删除
        if (CollectionUtils.isEmpty(vo.getMenuList()))
            return new ResponseData<>(ResponseCode.SUCCESS, Boolean.TRUE);
        //1,查询菜单列表
        List<AntMenu> menuList = this.list();
        if (CollectionUtils.isEmpty(menuList))
            return new ResponseData<>(ResponseCode.ERROR.getCode(), "系统尚无任何菜单", Boolean.FALSE);
        Map<Long, AntMenu> menuMap = new HashMap<>(menuList.size());
        menuList.forEach(item -> menuMap.put(item.getId(), item));
        Map<Long, RoleMenuEditVO> roleMenuMap = new HashMap<>(vo.getMenuList().size());
        List<AntRoleMenu> toAddList = new ArrayList<>();
        Set<Long> toAddSet = new HashSet<>();
        //新增列表
        if (!CollectionUtils.isEmpty(vo.getMenuList())) {
            vo.getMenuList().forEach(item -> {
                roleMenuMap.put(item.getMenuId(), item);
                //系统存在的菜单才处理
                if (null != menuMap.get(item.getMenuId())) {
                    toAddSet.addAll(getNodeAndAllParent(item.getMenuId(), menuMap));
                }
            });
        }
        //新增列表
        if (!CollectionUtils.isEmpty(toAddSet)) {
            toAddSet.forEach(item -> {
                AntRoleMenu roleMenu = new AntRoleMenu();
                roleMenu.setMenuId(item);
                roleMenu.setRoleId(vo.getRoleId());
                RoleMenuEditVO voItem = roleMenuMap.get(item);
                if (null != voItem && !CollectionUtils.isEmpty(voItem.getAbilities())) {
                    roleMenu.setAbility(JSON.toJSONString(voItem.getAbilities()));
                }
                toAddList.add(roleMenu);
            });
        }
        if (batchEditRoleMenu(vo.getRoleId(), toAddList))
            return new ResponseData<>(ResponseCode.SUCCESS, Boolean.TRUE);
        return new ResponseData<>(ResponseCode.BUSINESS_ERROR, false);
    }

    public IPage<AntMenu> getPage(AntMenu antMenu, int current, int size) {
        Page<AntMenu> page = new Page<>(current, size);
        IPage<AntMenu> iPage = this.baseMapper.selectPage(page, new QueryWrapper<>(antMenu));
        return page.setRecords(iPage.getRecords());
    }

    private Set<Long> getNodeAndAllParent(Long menuId, Map<Long, AntMenu> menuMap) {
        Set<Long> set = new HashSet<>();
        AntMenu menu = menuMap.get(menuId);
        if (menu == null)
            return set;
        set.add(menuId);
        if (!menu.getParentId().equals(0L))
            set.addAll(getNodeAndAllParent(menu.getParentId(), menuMap));
        return set;
    }

    private Boolean batchEditRoleMenu(Long roleId, List<AntRoleMenu> addList) {
        //删除原有的
        this.roleMenuMapper.deleteRoleMenu(roleId, null);
        //插入新提交的
        if (!CollectionUtils.isEmpty(addList))
            this.roleMenuMapper.batchAddRoleMenu(addList);
        return true;
    }

}
