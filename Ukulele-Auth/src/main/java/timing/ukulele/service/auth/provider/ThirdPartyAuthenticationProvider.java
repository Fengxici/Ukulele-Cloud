//package timing.ukulele.service.auth.provider;
//
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import timing.ukulele.service.auth.token.PhoneAuthenticationToken;
//import timing.ukulele.service.auth.token.ThirdPartyAuthenticationToken;
//
///**
// * 第三方平台用户信息登录
// */
//public class ThirdPartyAuthenticationProvider extends TimingAbstractUserDetailsAuthenticationProvider {
//
//    private UserDetailsService userDetailsService;
//
//    @Override
//    protected void additionalAuthenticationChecks(UserDetails user, Authentication authentication) throws AuthenticationException {
//
//        if (authentication.getCredentials() == null) {
//            this.logger.debug("Authentication failed: no credentials provided");
//            throw new BadCredentialsException(this.messages.getMessage("PhoneAuthenticationProvider.badCredentials", "Bad credentials"));
//        } else {
//            String presentedPassword = authentication.getCredentials().toString();
//        }
//    }
//
//    @Override
//    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
//        ThirdPartyAuthenticationToken result = new ThirdPartyAuthenticationToken(principal, authentication.getCredentials(),"third", user.getAuthorities());
//        result.setDetails(authentication.getDetails());
//        return result;
//    }
//
//    @Override
//    protected UserDetails retrieveUser(String phone, Authentication authentication) throws AuthenticationException {
//        UserDetails loadedUser;
//        try {
//            loadedUser = this.getUserDetailsService().loadUserByUsername(phone);
//        } catch (UsernameNotFoundException var6) {
//            throw var6;
//        } catch (Exception var7) {
//            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
//        }
//
//        if (loadedUser == null) {
//            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
//        } else {
//            return loadedUser;
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return PhoneAuthenticationToken.class.isAssignableFrom(authentication);
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
