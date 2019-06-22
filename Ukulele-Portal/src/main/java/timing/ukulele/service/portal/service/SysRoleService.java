package timing.ukulele.service.portal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import timing.ukulele.facade.portal.model.view.RoleVO;
import timing.ukulele.persistence.service.BaseService;
import timing.ukulele.service.portal.mapper.SysRoleMapper;
import timing.ukulele.service.portal.persistent.SysRole;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleService extends BaseService<SysRole> {

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
