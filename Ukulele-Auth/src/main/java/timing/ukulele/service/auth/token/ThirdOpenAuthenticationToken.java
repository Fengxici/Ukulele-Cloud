package timing.ukulele.service.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 第三方开放平台登录
 */
public class ThirdOpenAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final Integer type;

    /**
     * @param code 第三放OAuth平台验证码模式的code
     * @param type 第三方平台类型
     */
    public ThirdOpenAuthenticationToken(String code, Integer type) {
        super(null);
        this.principal = code;
        this.type = type;
        this.setAuthenticated(false);
    }

    public ThirdOpenAuthenticationToken(Object principal, Integer type, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.type = type;
        super.setAuthenticated(true);
    }

    public Integer getType() {
        return type;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }
}
