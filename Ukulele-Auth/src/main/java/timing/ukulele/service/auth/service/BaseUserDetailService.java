package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import timing.ukulele.api.model.GlobalUserModel;
import timing.ukulele.api.model.ModuleModel;
import timing.ukulele.api.model.RoleModel;
import timing.ukulele.api.service.feign.ISystemFeignService;
import timing.ukulele.common.data.ResponseData;
import timing.ukulele.service.auth.BaseUserDetail;
import timing.ukulele.web.pojo.ResponseCode;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseUserDetailService implements UserDetailsService {

    protected final ISystemFeignService systemService;
    protected final RedisTemplate<String, RoleModel> redisTemplate;
    protected final RedisTemplate<String, ModuleModel> resourcesTemplate;

    @Autowired
    public BaseUserDetailService(ISystemFeignService systemService, RedisTemplate<String, RoleModel> redisTemplate, RedisTemplate<String, ModuleModel> resourcesTemplate) {
        this.systemService = systemService;
        this.redisTemplate = redisTemplate;
        this.resourcesTemplate = resourcesTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {

        GlobalUserModel baseUser = getUser(var1);

        // 调用FeignClient查询角色
        ResponseData<List<RoleModel>> baseRoleListResponseData = systemService.getRoleByUserId(baseUser.getId());
        List<RoleModel> roles;
        if (baseRoleListResponseData.getData() == null || !ResponseCode.SUCCESS.getCode().equals(baseRoleListResponseData.getCode())) {
            log.error("查询角色失败！");
            roles = new ArrayList<>();
        } else {
            roles = baseRoleListResponseData.getData();
        }

        //调用FeignClient查询菜单
        ResponseData<List<ModuleModel>> baseModuleResourceListResponseData = systemService.getMenusByUserId(baseUser.getId());

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
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(baseUser.getAccount(),
                baseUser.getPassword(), baseUser.getEnable(), true, true, true, authorities);
        return new BaseUserDetail(baseUser, user);
    }

    protected abstract GlobalUserModel getUser(String var1);


    private List<GrantedAuthority> convertToAuthorities(GlobalUserModel baseUser, List<RoleModel> roles) {
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
