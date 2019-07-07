package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import timing.ukulele.common.data.ResponseCode;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.data.portal.view.MenuVO;
import timing.ukulele.data.portal.view.RoleVO;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.portal.feign.IMenuFeignFacade;
import timing.ukulele.facade.portal.feign.IRoleFeignFacade;
import timing.ukulele.facade.user.feign.IUserFeignFacade;
import timing.ukulele.service.auth.BaseUserDetail;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseUserDetailService implements UserDetailsService {

    protected final IRoleFeignFacade roleService;
    protected final IUserFeignFacade userService;
    protected final IMenuFeignFacade menuService;
    protected final RedisTemplate<String, RoleVO> redisTemplate;
    protected final RedisTemplate<String, MenuVO> resourcesTemplate;

    @Autowired
    public BaseUserDetailService(IUserFeignFacade userService, IRoleFeignFacade roleService, IMenuFeignFacade menuService, RedisTemplate<String, RoleVO> redisTemplate, RedisTemplate<String, MenuVO> resourcesTemplate) {
        this.userService = userService;
        this.roleService = roleService;
        this.menuService = menuService;
        this.redisTemplate = redisTemplate;
        this.resourcesTemplate = resourcesTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {

        UserVO baseUser = getUser(var1);

        // 调用FeignClient查询角色
        ResponseData<List<RoleVO>> baseRoleListResponseData = roleService.getRoleByUserId(baseUser.getId());
        List<RoleVO> roles;
        if (baseRoleListResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseRoleListResponseData.getCode())) {
            log.error("查询角色失败！");
            roles = new ArrayList<>();
        } else {
            roles = baseRoleListResponseData.getData();
        }

        //调用FeignClient查询菜单
        ResponseData<List<MenuVO>> baseModuleResourceListResponseData = menuService.getMenuByUserId(baseUser.getId());

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(baseUser, roles);

        // 存储菜单到redis
        if (ResponseCode.SUCCESS.getCode().equals(baseModuleResourceListResponseData.getCode()) && baseModuleResourceListResponseData.getData() != null) {
            resourcesTemplate.delete(baseUser.getId() + "-menu");
            baseModuleResourceListResponseData.getData().forEach(e -> {
                resourcesTemplate.opsForList().leftPush(baseUser.getId() + "-menu", e);
            });
        }
        boolean enabled = baseUser.getDeleted() != null && !baseUser.getDeleted();
        // 返回带有用户权限信息的User
        User user = new User(baseUser.getUsername(),
                baseUser.getPassword(), enabled, true, true, true, authorities);
        return new BaseUserDetail(baseUser, user);
    }

    protected abstract UserVO getUser(String var1);


    private List<GrantedAuthority> convertToAuthorities(UserVO baseUser, List<RoleVO> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 清除 Redis 中用户的角色
        redisTemplate.delete(baseUser.getId().toString());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleCode());
            authorities.add(authority);
            //存储角色到redis
            redisTemplate.opsForList().rightPush(baseUser.getId().toString(), e);
        });
        return authorities;
    }
}
