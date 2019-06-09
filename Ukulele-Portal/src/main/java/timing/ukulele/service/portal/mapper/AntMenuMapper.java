package timing.ukulele.service.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import timing.ukulele.facade.portal.model.persistent.AntMenu;
import timing.ukulele.persistence.mapper.BaseMapper;

import java.util.List;
@Mapper
public interface AntMenuMapper extends BaseMapper<AntMenu> {
    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    List<AntMenu> findMenuByRoleName(@Param("role") String role);

    int deleteRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int addRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int deleteRoleMenuByMenuId(@Param("menuId") Long menuId);

    List<AntMenu> getMenuByUserId(@Param("userId") Long userId);
}
