package timing.ukulele.service.auth.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 第三方平台验证token
 */
public class ThirdPartyAuthenticationToken extends TimingAuthenticationToken {

    public ThirdPartyAuthenticationToken(Object principal, Object credentials, String type, String extension) {
        super(principal, credentials, type, extension);
    }

    public ThirdPartyAuthenticationToken(Object principal, Object credentials, String type, String extension, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, type, extension, authorities);
    }

}
