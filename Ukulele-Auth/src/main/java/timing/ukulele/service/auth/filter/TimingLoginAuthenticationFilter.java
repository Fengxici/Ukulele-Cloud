package timing.ukulele.service.auth.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import timing.ukulele.service.auth.token.PhoneAuthenticationToken;
import timing.ukulele.service.auth.token.QrAuthenticationToken;
import timing.ukulele.service.auth.token.ThirdPartyAuthenticationToken;
import timing.ukulele.service.auth.token.TimingAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static timing.ukulele.service.auth.Constant.*;

/**
 * 自定义登陆filter，新增登陆方式：验证码、二维码扫码、账号密码、第三方平台
 * 此filter 为生成自定义的 TimingAuthenticationToken
 */
public class TimingLoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String type = obtainParameter(request, SPRING_SECURITY_RESTFUL_TYPE_KEY);
        AbstractAuthenticationToken authRequest;
        String principal;
        String credentials;

        // 手机验证码登陆
        if(SPRING_SECURITY_RESTFUL_TYPE_PHONE.equals(type)){
            principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_PHONE_KEY);
            credentials = obtainParameter(request, SPRING_SECURITY_RESTFUL_VERIFY_CODE_KEY);
            principal = principal.trim();
            authRequest = new PhoneAuthenticationToken(principal, credentials,SPRING_SECURITY_RESTFUL_TYPE_PHONE,null);
        }
        // 二维码扫码登陆
        else if(SPRING_SECURITY_RESTFUL_TYPE_QR.equals(type)){
            principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_QR_CODE_KEY);
            authRequest = new QrAuthenticationToken(principal, null,SPRING_SECURITY_RESTFUL_TYPE_QR,null);
        }
        // 第三方
        else if(SPRING_SECURITY_RESTFUL_TYPE_THIRD.equals(type)){
            principal =obtainParameter(request,SPRING_SECURITY_RESTFUL_USER_ID_KEY);
            credentials= obtainParameter(request,SPRING_SECURITY_RESTFUL_PLAT_ID_KEY);
            String plat = obtainParameter(request,SPRING_SECURITY_RESTFUL_PLAT_KEY);
//            authRequest = new ThirdPartyAuthenticationToken(principal,credentials,SPRING_SECURITY_RESTFUL_TYPE_THIRD,plat);
            authRequest = new TimingAuthenticationToken(principal,credentials,SPRING_SECURITY_RESTFUL_TYPE_THIRD,plat);
        }
        // 账号密码登陆
        else {
            principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_USERNAME_KEY);
            credentials = obtainParameter(request, SPRING_SECURITY_RESTFUL_PASSWORD_KEY);
            principal = principal.trim();
            authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
        }
        // Allow subclasses to set the "details" property
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
        String result =  request.getParameter(parameter);
        return result == null ? "" : result;
    }
}
