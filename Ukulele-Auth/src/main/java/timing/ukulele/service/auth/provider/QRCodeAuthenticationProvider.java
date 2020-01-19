package timing.ukulele.service.auth.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import timing.ukulele.common.constant.PrefixConstant;
import timing.ukulele.redisson.cache.CacheManager;
import timing.ukulele.service.auth.service.TimingUserDetailService;
import timing.ukulele.service.auth.token.QRCodeAuthenticationToken;
import timing.ukulele.service.auth.token.ThirdOpenAuthenticationToken;

/**
 * TODO
 */
public class QRCodeAuthenticationProvider implements AuthenticationProvider {
    private final TimingUserDetailService userDetailService;
    private final CacheManager cacheManager;

    public QRCodeAuthenticationProvider(TimingUserDetailService userDetailService, CacheManager cacheManager) {
        this.userDetailService = userDetailService;
        this.cacheManager = cacheManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //这个authentication就是ThirdOpenAuthenticationToken
        QRCodeAuthenticationToken authenticationToken = (QRCodeAuthenticationToken) authentication;
        Object code = cacheManager.get(PrefixConstant.QR_CONNECT_ID + authenticationToken.getConnectId());
        if (null == code)
            throw new InvalidGrantException("链接过期");
        String codeStr = (String) code;
        if (!codeStr.equals(authenticationToken.getCode())) {
            throw new InvalidGrantException("链接不正确");
        }
        UserDetails user = userDetailService.loadUserByUsername((String) authenticationToken.getPrincipal());
        //这时候已经认证成功了
        if (user == null) {
            return null;
        }
        QRCodeAuthenticationToken authenticationResult = new QRCodeAuthenticationToken(user, authenticationToken.getCode(), authenticationToken.getConnectId(), user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return QRCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
