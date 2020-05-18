package timing.ukulele.service.auth.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.util.StringUtils;
import timing.ukulele.common.constant.PrefixConstant;
import timing.ukulele.redisson.cache.CacheManager;
import timing.ukulele.service.auth.service.TimingUserDetailService;
import timing.ukulele.service.auth.token.SmsCodeAuthenticationToken;

/**
 * @author fengxici
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

    private final CacheManager cacheManager;
    private final TimingUserDetailService userDetailService;

    public SmsCodeAuthenticationProvider(CacheManager cacheManager, TimingUserDetailService userDetailService) {
        this.cacheManager = cacheManager;
        this.userDetailService = userDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //这个authentication就是SmsCodeAuthenticationToken
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        //校验手机号
        Object code = cacheManager.get(PrefixConstant.SMS_CODE + authenticationToken.getPrincipal());
        String cachedCode = null == code ? null : code.toString();
        String smsCode = authenticationToken.getCode();
        if (StringUtils.isEmpty(cachedCode) || !smsCode.equals(cachedCode)) {
            throw new InvalidGrantException("验证码错误");
        }
        UserDetails user = userDetailService.loadUserByPhone((String) authenticationToken.getPrincipal());
        //这时候已经认证成功了
        if (user == null) {
            return null;
        }
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user, smsCode, user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
