package timing.ukulele.service.auth.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import timing.ukulele.service.auth.service.TimingUserDetailService;
import timing.ukulele.service.auth.token.ThirdOpenAuthenticationToken;

public class ThirdOpenAuthenticationProvider implements AuthenticationProvider {
    private final TimingUserDetailService userDetailService;

    public ThirdOpenAuthenticationProvider(TimingUserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //这个authentication就是ThirdOpenAuthenticationToken
        ThirdOpenAuthenticationToken authenticationToken = (ThirdOpenAuthenticationToken) authentication;
        UserDetails user = userDetailService.loadUserByThirdOpenCode((String) authenticationToken.getPrincipal(), authenticationToken.getType());
        //这时候已经认证成功了
        if (user == null) {
            return null;
        }
        ThirdOpenAuthenticationToken authenticationResult = new ThirdOpenAuthenticationToken(user, authenticationToken.getType(), user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ThirdOpenAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
