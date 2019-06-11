package timing.ukulele.service.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.view.MenuVO;
import timing.ukulele.persistence.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    List<SysMenu> findMenuByRoleName(@Param("role") String role);

    int deleteRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int addRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    List<SysMenu> getMenuByUserId(@Param("userId") Long userId);
}
