//package timing.ukulele.service.auth.provider;
//
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import timing.ukulele.service.auth.token.QrAuthenticationToken;
//
///**
// * 二维码扫码登陆
// */
//public class QrAuthenticationProvider extends TimingAbstractUserDetailsAuthenticationProvider {
//
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails var1, Authentication authentication) throws AuthenticationException {
//
//        // 二维码只需要根据 SPRING_SECURITY_RESTFUL_QR_CODE_KEY 查询到用户即可，所以此处无需验证
//    }
//
//    @Override
//    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
//        QrAuthenticationToken result = new QrAuthenticationToken(principal, authentication.getCredentials(),"qr", user.getAuthorities());
//        result.setDetails(authentication.getDetails());
//        return result;
//    }
//
//    @Override
//    protected UserDetails retrieveUser(String qrCode, Authentication authentication) throws AuthenticationException {
//        UserDetails loadedUser;
//        try {
//            loadedUser = this.getUserDetailsService().loadUserByUsername(qrCode);
//        } catch (UsernameNotFoundException var6) {
//            throw var6;
//        } catch (Exception var7) {
//            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
//        }
//
//        if(loadedUser == null) {
//            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
//        } else {
//            return loadedUser;
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return QrAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//
//    public UserDetailsService getUserDetailsService() {
//        return userDetailsService;
//    }
//
//    public void setUserDetailsService(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//}
