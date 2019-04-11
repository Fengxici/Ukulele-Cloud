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
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.facade.portal.api.feign.IMenuFeignService;
import timing.ukulele.facade.portal.api.feign.IRoleFeignService;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.persistent.SysRole;
import timing.ukulele.facade.user.api.feign.IUserFeignService;
import timing.ukulele.facade.user.model.persistent.SysUser;
import timing.ukulele.service.auth.BaseUserDetail;
import timing.ukulele.web.pojo.ResponseCode;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseUserDetailService implements UserDetailsService {

    protected final IRoleFeignService roleService;
    protected final IUserFeignService userService;
    protected final IMenuFeignService menuService;
    protected final RedisTemplate<String, SysRole> redisTemplate;
    protected final RedisTemplate<String, SysMenu> resourcesTemplate;

    @Autowired
    public BaseUserDetailService(IUserFeignService userService, IRoleFeignService roleService, IMenuFeignService menuService, RedisTemplate<String, SysRole> redisTemplate, RedisTemplate<String, SysMenu> resourcesTemplate) {
        this.userService = userService;
        this.roleService = roleService;
        this.menuService = menuService;
        this.redisTemplate = redisTemplate;
        this.resourcesTemplate = resourcesTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {

        SysUser baseUser = getUser(var1);

        // 调用FeignClient查询角色
        ResponseData<List<SysRole>> baseRoleListResponseData = roleService.getRoleByUserId(baseUser.getId());
        List<SysRole> roles;
        if (baseRoleListResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseRoleListResponseData.getCode())) {
            log.error("查询角色失败！");
            roles = new ArrayList<>();
        } else {
            roles = baseRoleListResponseData.getData();
        }

        //调用FeignClient查询菜单
        ResponseData<List<SysMenu>> baseModuleResourceListResponseData = menuService.getMenuByUserId(baseUser.getId());

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(baseUser, roles);

        // 存储菜单到redis
        if (ResponseCode.SUCCESS.getCode().equals(baseModuleResourceListResponseData.getCode()) && baseModuleResourceListResponseData.getData() != null) {
            resourcesTemplate.delete(baseUser.getId() + "-menu");
            baseModuleResourceListResponseData.getData().forEach(e -> {
                resourcesTemplate.opsForList().leftPush(baseUser.getId() + "-menu", e);
            });
        }

        // 返回带有用户权限信息的User
        User user = new User(baseUser.getUsername(),
                baseUser.getPassword(), baseUser.getEnable(), true, true, true, authorities);
        return new BaseUserDetail(baseUser, user);
    }

    protected abstract SysUser getUser(String var1);


    private List<GrantedAuthority> convertToAuthorities(SysUser baseUser, List<SysRole> roles) {
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
