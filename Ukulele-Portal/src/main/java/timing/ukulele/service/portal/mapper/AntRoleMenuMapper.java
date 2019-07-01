package timing.ukulele.service.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import timing.ukulele.service.portal.persistent.AntRoleMenu;

import java.util.List;

@Mapper
@Repository
public interface AntRoleMenuMapper {

    List<AntRoleMenu> selectRoleMenu(@Param("roleId") Long roleId);

    int deleteRoleMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int deleteRoleMenuByMenuId(@Param("menuId") Long menuId);

    int batchDeleteRoleMenu(@Param("roleId") Long roleId, @Param("menus") List<Long> menus);

    int batchAddRoleMenu(@Param("roleMenus") List<AntRoleMenu> list);

    int batchUpdateRoleMenu(@Param("roleMenus") List<AntRoleMenu> list);
}
