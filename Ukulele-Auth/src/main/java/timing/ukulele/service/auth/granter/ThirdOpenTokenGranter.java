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
import timing.ukulele.service.auth.token.QRCodeAuthenticationToken;
import timing.ukulele.service.auth.token.ThirdOpenAuthenticationToken;

import java.util.LinkedHashMap;
import java.util.Map;

import static timing.ukulele.service.auth.Constant.SPRING_SECURITY_PLAT_CODE_KEY;
import static timing.ukulele.service.auth.Constant.SPRING_SECURITY_PLAT_TYPE_KEY;

public class ThirdOpenTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "third_open";
    private static final int CODE_LENGTH = 6;
    private final AuthenticationManager authenticationManager;

    public ThirdOpenTokenGranter(AuthorizationServerTokenServices tokenServices,
                                 ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, final AuthenticationManager authenticationManager) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,
                                                           TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String code = parameters.get(SPRING_SECURITY_PLAT_CODE_KEY);  //客户端提交的验证码
        String type = parameters.get(SPRING_SECURITY_PLAT_TYPE_KEY);//第三方平台类型
        if (StringUtils.isEmpty(code) ) {
            throw new InvalidGrantException("授权码错误");
        }
        if (StringUtils.isEmpty(type)) {
            //todo
            throw new InvalidGrantException("第三方平台类型错误");
        }
        Authentication userAuth = new ThirdOpenAuthenticationToken(code, Integer.valueOf(type));
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);//调用上面的provide 验证
        } catch (AccountStatusException | BadCredentialsException ase) {
//covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        } // If the username/password are wrong the spec says we should send 400/invalid grant
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + code);
        }
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
