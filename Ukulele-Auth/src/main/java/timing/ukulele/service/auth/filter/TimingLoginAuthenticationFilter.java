package timing.ukulele.service.auth.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import timing.ukulele.service.auth.token.QRCodeAuthenticationToken;
import timing.ukulele.service.auth.token.SmsCodeAuthenticationToken;
import timing.ukulele.service.auth.token.ThirdOpenAuthenticationToken;
import timing.ukulele.service.auth.token.TimingAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static timing.ukulele.service.auth.Constant.*;

/**
 * 自定义登陆filter，新增登陆方式：验证码、二维码扫码、账号密码、第三方平台
 * 此filter 为生成自定义的 TimingAuthenticationToken
 * @author fengxici
 */
public class TimingLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String type = obtainParameter(request, SPRING_SECURITY_TYPE_KEY);
        AbstractAuthenticationToken authRequest = null;
        String principal;
        String credentials;

        // 手机验证码登陆
        switch (type) {
            case SPRING_SECURITY_TYPE_PHONE:
                principal = obtainParameter(request, SPRING_SECURITY_PHONE_KEY);
                credentials = obtainParameter(request, SPRING_SECURITY_SMS_CODE_KEY);
                principal = principal.trim();
                authRequest = new SmsCodeAuthenticationToken(principal, credentials);
                break;
            // 二维码扫码登陆
            case SPRING_SECURITY_TYPE_QR:
                principal = obtainParameter(request, SPRING_SECURITY_CONNECT_USERNAME_KEY);
                String code = obtainParameter(request, SPRING_SECURITY_QR_CODE_KEY);
                String connectId = obtainParameter(request, SPRING_SECURITY_CONNECT_ID_KEY);
                authRequest = new QRCodeAuthenticationToken(principal, code, connectId);
                break;
            // 第三方
            case SPRING_SECURITY_TYPE_THIRD:
                principal = obtainParameter(request, SPRING_SECURITY_PLAT_CODE_KEY);
//            credentials = obtainParameter(request, SPRING_SECURITY_PLAT_CODE_KEY);
                String plat = obtainParameter(request, SPRING_SECURITY_PLAT_TYPE_KEY);
                if (!StringUtils.isEmpty(plat)) {
                    authRequest = new ThirdOpenAuthenticationToken(principal, Integer.valueOf(plat));
                }
                break;
            // 账号密码登陆
            default:
                principal = obtainParameter(request, SPRING_SECURITY_USERNAME_KEY);
                credentials = obtainParameter(request, SPRING_SECURITY_PASSWORD_KEY);
                principal = principal.trim();
                authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
                break;
        }
        // Allow subclasses to set the "details" property
        assert authRequest != null;
        setDetails(request, authRequest);
        AuthenticationManager authenticationManager = this.getAuthenticationManager();
        Authentication authenticate = authenticationManager.authenticate(authRequest);
        return authenticate;
    }

    private void setDetails(HttpServletRequest request,
                            AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        String result = request.getParameter(parameter);
        return result == null ? "" : result;
    }
}
