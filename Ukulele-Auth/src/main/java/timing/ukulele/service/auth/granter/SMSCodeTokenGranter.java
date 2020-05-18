package timing.ukulele.service.auth.granter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.util.StringUtils;
import timing.ukulele.common.util.RegexUtil;
import timing.ukulele.service.auth.token.SmsCodeAuthenticationToken;

import java.util.LinkedHashMap;
import java.util.Map;

import static timing.ukulele.service.auth.Constant.*;

/**
 * @author fengxici
 */
public class SMSCodeTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "sms_code";
    private final AuthenticationManager authenticationManager;

    public SMSCodeTokenGranter(AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, final AuthenticationManager authenticationManager) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,
                                                           TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        //客户端提交的用户名
        String userMobileNo = parameters.get(SPRING_SECURITY_PHONE_KEY);
        if (!RegexUtil.checkMobileNumber(userMobileNo)) {
            throw new InvalidGrantException("手机号码格式错误");
        }
        //客户端提交的验证码
        String code = parameters.get(SPRING_SECURITY_SMS_CODE_KEY);
        if (StringUtils.isEmpty(code) || code.length() != SPRING_SECURITY_SMS_CODE_LENGTH) {
            throw new InvalidGrantException("验证码错误");
        }
        Authentication userAuth = new SmsCodeAuthenticationToken(userMobileNo, code);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            //调用上面的provide 验证
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException ase) {
            throw new InvalidGrantException(ase.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + userMobileNo);
        }
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
