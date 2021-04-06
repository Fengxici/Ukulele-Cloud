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
import timing.ukulele.service.auth.token.SmsCodeAuthenticationToken;

import java.util.LinkedHashMap;
import java.util.Map;

import static timing.ukulele.service.auth.Constant.*;

/**
 * @author fengxici
 */
public class QRCodeTokenGranter extends AbstractTokenGranter {

    private static final String GRANT_TYPE = "qr_code";
    private final AuthenticationManager authenticationManager;

    public QRCodeTokenGranter(AuthorizationServerTokenServices tokenServices,
                              ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, final AuthenticationManager authenticationManager) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,
                                                           TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        //客户端提交的连接标识
        String connectId = parameters.get(SPRING_SECURITY_CONNECT_ID_KEY);
        //客户端提交的用户名
        String username = parameters.get(SPRING_SECURITY_CONNECT_USERNAME_KEY);
        //客户端提交的验证码
        String code = parameters.get(SPRING_SECURITY_QR_CODE_KEY);
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(connectId) || StringUtils.isEmpty(username)) {
            throw new InvalidGrantException("参数有误");
        }
        Authentication userAuth = new QRCodeAuthenticationToken(username, code, connectId);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            //调用上面的provide 验证
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException | BadCredentialsException ase) {
            throw new InvalidGrantException(ase.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + connectId);
        }
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
