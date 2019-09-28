package timing.ukulele.service.portal.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import timing.ukulele.data.portal.data.RolePermission;
import timing.ukulele.data.portal.view.RoleVO;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.AntRoleMenuMapper;
import timing.ukulele.service.portal.mapper.SysRoleMapper;
import timing.ukulele.service.portal.persistent.AntMenu;
import timing.ukulele.service.portal.persistent.AntRoleMenu;
import timing.ukulele.service.portal.persistent.SysRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SysRoleService extends BaseService<SysRole> {
    private final AntMenuService menuService;
    private final AntRoleMenuMapper roleMenuMapper;

    @Autowired
    public SysRoleService(AntMenuService menuService, AntRoleMenuMapper roleMenuMapper) {
        this.menuService = menuService;
        this.roleMenuMapper = roleMenuMapper;
    }

    /**
     * 添加角色
     *
     * @param roleDto 角色信息
     * @return 成功、失败
     */
    public Boolean insertRole(RoleVO roleDto) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        this.baseMapper.insert(sysRole);
        return true;
    }

    /**
     * 更新角色
     *
     * @param roleDto 含有部门信息
     * @return 成功、失败
     */
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateRoleById(RoleVO roleDto) {
        //更新角色信息
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDto, sysRole);
        this.baseMapper.updateById(sysRole);

        return true;
    }

    /**
     * 通过部门ID查询角色列表
     *
     * @param deptId 部门ID
     * @return 角色列表
     */
    public List<SysRole> selectListByDeptId(Long deptId) {
        return ((SysRoleMapper) this.baseMapper).selectListByDeptId(deptId);
    }

    public List<SysRole> getRoleByUserId(Long id) {
        return ((SysRoleMapper) this.baseMapper).getRoleByUserId(id);
    }


    public Boolean deleteUserRole(Long userId, Long roleId) {
        return ((SysRoleMapper) this.baseMapper).deleteUserRole(userId, roleId) >= 0;
    }

    public Boolean addUserRole(Long userId, Long roleId) {
        return ((SysRoleMapper) this.baseMapper).addUserRole(userId, roleId) > 0;
    }

    public Map<String, Map<String, RolePermission>> rolePermission(List<String> roleCode) {
        List<AntMenu> menuList = menuService.list();
        if (CollectionUtils.isEmpty(menuList))
            return null;
        Map<Long, AntMenu> menuMap = menuList.stream().collect(Collectors.toMap(AntMenu::getId, a -> a, (k1, k2) -> k1));
        List<SysRole> roleList = list();
        if (CollectionUtils.isEmpty(roleList))
            return null;
        Map<Long, SysRole> roleMap = roleList.stream().collect(Collectors.toMap(SysRole::getId, a -> a, (k1, k2) -> k1));
        List<AntRoleMenu> roleMenuList = roleMenuMapper.selectAll();
        if (CollectionUtils.isEmpty(roleMenuList))
            return null;
        Map<String, Map<String, RolePermission>> roleMenuPermissionMap = new HashMap<>(roleMap.size());
        roleMenuList.forEach(antRoleMenu -> {
            AntMenu menu = menuMap.get(antRoleMenu.getMenuId());
            if (null == menu || StringUtils.isEmpty(menu.getLink()))
                return;
            if (null == roleMap.get(antRoleMenu.getRoleId()))
                return;
            if (StringUtils.isEmpty(menu.getAcl()))
                return;
            if (StringUtils.isEmpty(antRoleMenu.getAbility()))
                return;
            Map<String, RolePermission> rolePermissionMap = roleMenuPermissionMap.computeIfAbsent(roleMap.get(antRoleMenu.getRoleId()).getRoleCode(), k -> new HashMap<>());
            String router = menuMap.get(antRoleMenu.getMenuId()).getLink();
            RolePermission rolePermission = new RolePermission();
            rolePermission.setAcl(JSON.parseArray(menu.getAcl(), String.class));
            rolePermission.setAbility(JSON.parseArray(antRoleMenu.getAbility(), String.class));
            rolePermissionMap.put(router, rolePermission);
        });
        if (!CollectionUtils.isEmpty(roleCode)) {
            Map<String, Map<String, RolePermission>> selectedRoleMenuPermissionMap = new HashMap<>(roleCode.size());
            roleCode.forEach(item -> {
                selectedRoleMenuPermissionMap.put(item, roleMenuPermissionMap.get(item));
            });
            return selectedRoleMenuPermissionMap;
        }
        return roleMenuPermissionMap;
    }

    public IPage<RoleVO> getPage(SysRole role, int current, int size) {
        Page<SysRole> page = new Page<>(current, size);
        IPage<SysRole> iPage = this.baseMapper.selectPage(page, new QueryWrapper<>(role));
        Page<RoleVO> voPage = new Page<>(current, size);
        if (iPage != null && !CollectionUtils.isEmpty(iPage.getRecords())) {
            List<RoleVO> voList = new ArrayList<>(iPage.getRecords().size());
            iPage.getRecords().forEach(po -> {
                RoleVO vo = new RoleVO();
                BeanUtils.copyProperties(po, vo);
                voList.add(vo);
            });
            voPage.setRecords(voList);
        }
        return voPage;
    }
}
