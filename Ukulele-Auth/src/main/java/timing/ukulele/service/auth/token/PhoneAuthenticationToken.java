package timing.ukulele.service.auth.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 手机验证码token
 */
public class PhoneAuthenticationToken extends TimingAuthenticationToken {

    public PhoneAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PhoneAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
