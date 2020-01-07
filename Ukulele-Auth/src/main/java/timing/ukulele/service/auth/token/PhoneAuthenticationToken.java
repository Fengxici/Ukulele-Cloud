package timing.ukulele.service.auth.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 手机验证码token
 */
public class PhoneAuthenticationToken extends TimingAuthenticationToken {

    public PhoneAuthenticationToken(Object principal, Object credentials, String type, String extension) {
        super(principal, credentials, type, extension);
    }

    public PhoneAuthenticationToken(Object principal, Object credentials, String type, String extension, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, type, extension, authorities);
    }

}
