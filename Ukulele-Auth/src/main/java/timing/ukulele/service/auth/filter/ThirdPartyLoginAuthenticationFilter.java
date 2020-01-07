//package timing.ukulele.service.auth.filter;
//
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import timing.ukulele.service.auth.token.PhoneAuthenticationToken;
//import timing.ukulele.service.auth.token.ThirdPartyAuthenticationToken;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 第三方平台登陆：
// * post: /third?platId=32432434355435dsadcadq12d&userId=1&plat=1
// */
//public class ThirdPartyLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//    private static final String SPRING_SECURITY_RESTFUL_THIRD_KEY = "platId";
//    private static final String SPRING_SECURITY_RESTFUL_USER_KEY = "userId";
//    private static final String SPRING_SECURITY_RESTFUL_PLAT_KEY = "plat";
//    private static final String SPRING_SECURITY_RESTFUL_LOGIN_URL = "/third";
//    private boolean postOnly = true;
//
//    public ThirdPartyLoginAuthenticationFilter() {
//        super(new AntPathRequestMatcher(SPRING_SECURITY_RESTFUL_LOGIN_URL, "POST"));
//    }
//
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        if (postOnly && !request.getMethod().equals("POST")) {
//            throw new AuthenticationServiceException(
//                    "Authentication method not supported: " + request.getMethod());
//        }
//
//        AbstractAuthenticationToken authRequest;
//        String principal;
//        String credentials;
//        String type;
//        // 第三方平台登陆
//        principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_THIRD_KEY);
//        credentials = obtainParameter(request, SPRING_SECURITY_RESTFUL_USER_KEY);
//        principal = principal.trim();
//        authRequest = new ThirdPartyAuthenticationToken(principal, credentials, "third");
//
//        // Allow subclasses to set the "details" property
//        setDetails(request, authRequest);
//        return this.getAuthenticationManager().authenticate(authRequest);
//    }
//
//    private void setDetails(HttpServletRequest request,
//                            AbstractAuthenticationToken authRequest) {
//        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
//    }
//
//    private String obtainParameter(HttpServletRequest request, String parameter) {
//        String result = request.getParameter(parameter);
//        return result == null ? "" : result;
//    }
//}
