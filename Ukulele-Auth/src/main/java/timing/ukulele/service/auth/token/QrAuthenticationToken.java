package timing.ukulele.service.auth.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 二维码Token
 */
public class QrAuthenticationToken extends TimingAuthenticationToken {
    public QrAuthenticationToken(Object principal, Object credentials, String type,String extension) {
        super(principal, credentials, type,extension);
    }

    public QrAuthenticationToken(Object principal, Object credentials, String type, String extension,Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, type, extension,authorities);
    }
}
