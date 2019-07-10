package timing.ukulele.service.auth;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import timing.ukulele.data.user.view.UserVO;

import java.util.Collection;

/**
 * 包装org.springframework.security.core.userdetails.User类
 * 新增 baseUser 用于生成 jwt 的用户信息
 */
public class BaseUserDetail implements UserDetails, CredentialsContainer {

    private final UserVO baseUser;
    private final User user;

    public BaseUserDetail(UserVO baseUser, User user) {
        this.baseUser = baseUser;
        this.user = user;
    }

    @Override
    public void eraseCredentials() {
        user.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public UserVO getBaseUser() {
        return baseUser;
    }
}
