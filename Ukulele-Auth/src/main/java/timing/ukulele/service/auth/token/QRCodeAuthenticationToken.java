package timing.ukulele.service.auth.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 扫码登陆
 * @author fengxici
 */
public class QRCodeAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final String code;
    private final String connectId;


    public QRCodeAuthenticationToken(String username, String code, String connectId) {
        super(null);
        this.principal = username;
        this.code = code;
        this.setAuthenticated(false);
        this.connectId = connectId;
    }

    public QRCodeAuthenticationToken(Object principal, String code, String connectId, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.code = code;
        this.connectId = connectId;
        super.setAuthenticated(true);
    }

    public String getCode() {
        return code;
    }

    public String getConnectId() {
        return connectId;
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
