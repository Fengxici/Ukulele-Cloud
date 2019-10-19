package timing.ukulele.service.portal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import timing.ukulele.persistence.mapper.BaseMapper;
import timing.ukulele.service.portal.persistent.AntMenu;
import timing.ukulele.service.portal.persistent.MenuPermission;
import java.util.List;
@Mapper
@Repository
public interface AntMenuMapper extends BaseMapper<AntMenu> {
    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    List<AntMenu> findMenuByRoleName(@Param("role") String role);

    List<MenuPermission> findRoleMenuAbility(@Param("role") String role);
    
    List<AntMenu> getMenuByUserId(@Param("userId") Long userId);
}
