package timing.ukulele.api.service.portal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import timing.ukulele.api.model.portal.SysMenu;

import java.util.Set;

public interface IMenuService {
    /**
     * 通过角色名查询菜单
     *
     * @param role 角色名称
     * @return 菜单列表
     */
    @GetMapping(value = "/menu/findMenuByRole/{role}")
    Set<SysMenu> findMenuByRole(@PathVariable("role") String role);
}
