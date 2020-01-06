package timing.ukulele.service.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import timing.ukulele.data.user.view.UserVO;
import timing.ukulele.facade.user.feign.IUserFeignFacade;
import timing.ukulele.service.auth.BaseUserDetail;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class BaseUserDetailService implements UserDetailsService {

    protected final IUserFeignFacade userService;

    @Autowired
    public BaseUserDetailService(IUserFeignFacade userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String var1) throws UsernameNotFoundException {

        UserVO baseUser = getUser(var1);

        List<String> roles;
        if (!StringUtils.isEmpty(baseUser.getLabel()))
            roles = baseUser.getLabel();
        else
            roles = new ArrayList<>(0);

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(roles);
        boolean enabled = baseUser.getDeleted() != null && !baseUser.getDeleted();
        // 返回带有用户权限信息的User
        User user = new User(baseUser.getUsername(),
                baseUser.getPassword(), enabled, true, true, true, authorities);
        return new BaseUserDetail(baseUser, user);
    }

    protected abstract UserVO getUser(String username);


    private List<GrantedAuthority> convertToAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.toUpperCase());
            authorities.add(authority);
        });
        return authorities;
    }
}