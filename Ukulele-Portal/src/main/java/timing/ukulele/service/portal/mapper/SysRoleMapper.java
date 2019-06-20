package timing.ukulele.service.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import timing.ukulele.persistence.mapper.BaseMapper;
import timing.ukulele.service.portal.persistent.SysRole;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 查询角色列表含有部门信息
     *
     * @param condition 条件
     * @return List
     */
    List<Object> selectRolePage(Map<String, Object> condition);

    /**
     * 通过部门ID查询角色列表
     *
     * @param deptId 部门ID
     * @return 角色列表
     */
    List<SysRole> selectListByDeptId(Long deptId);

    List<SysRole> getRoleByUserId(@Param("userId") Long userId);

    int deleteUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    int addUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
