package timing.ukulele.service.auth.provider;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import timing.ukulele.service.auth.service.TimingUserDetailService;
import timing.ukulele.service.auth.token.QRCodeAuthenticationToken;
import timing.ukulele.service.auth.token.SmsCodeAuthenticationToken;
import timing.ukulele.service.auth.token.ThirdOpenAuthenticationToken;
import timing.ukulele.service.auth.token.TimingAuthenticationToken;

public class TimingAuthenticationProvider extends TimingAbstractUserDetailsAuthenticationProvider {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, Authentication authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("TimingAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (authentication instanceof TimingAuthenticationToken) {//用户名密码

            } else if (authentication instanceof SmsCodeAuthenticationToken) {//手机验证码

            } else if (authentication instanceof ThirdOpenAuthenticationToken) {//第三方开放平台

            } else if (authentication instanceof QRCodeAuthenticationToken) {//二维码

            } else if (authentication instanceof UsernamePasswordAuthenticationToken) {
                if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                    this.logger.debug("Authentication failed: password does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }
            }
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
//        if (authentication instanceof UsernamePasswordAuthenticationToken) {
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials(), user.getAuthorities());
//            token.setDetails(authentication.getDetails());
//            return token;
//        } else {
//            ((TimingAuthenticationToken) authentication).setDetails(authentication.getDetails());
//            TimingAuthenticationToken token = new TimingAuthenticationToken(principal, authentication.getCredentials(), user.getAuthorities());
//            token.setDetails(authentication.getDetails());
//            return token;
//        }
        if (authentication instanceof TimingAuthenticationToken) {//用户名密码
            TimingAuthenticationToken timingToken = (TimingAuthenticationToken) authentication;
            timingToken.setDetails(authentication.getDetails());
            TimingAuthenticationToken token = new TimingAuthenticationToken(principal, timingToken.getCredentials(), user.getAuthorities());
            token.setDetails(authentication.getDetails());
            return token;
        } else if (authentication instanceof SmsCodeAuthenticationToken) {//手机验证码
            SmsCodeAuthenticationToken smsToken = (SmsCodeAuthenticationToken) authentication;
            smsToken.setDetails(authentication.getDetails());
            SmsCodeAuthenticationToken token = new SmsCodeAuthenticationToken(smsToken.getPrincipal(), smsToken.getCode(), user.getAuthorities());
            token.setDetails(authentication.getDetails());
            return token;
        } else if (authentication instanceof ThirdOpenAuthenticationToken) {//第三方开放平台
            ThirdOpenAuthenticationToken thirdToken = (ThirdOpenAuthenticationToken) authentication;
            thirdToken.setDetails(authentication.getDetails());
            ThirdOpenAuthenticationToken token = new ThirdOpenAuthenticationToken(thirdToken.getPrincipal(), thirdToken.getType(), user.getAuthorities());
            token.setDetails(authentication.getDetails());
            return token;
        } else if (authentication instanceof QRCodeAuthenticationToken) {//二维码
            QRCodeAuthenticationToken qrToken = (QRCodeAuthenticationToken) authentication;
            qrToken.setDetails(authentication.getDetails());
            QRCodeAuthenticationToken token = new QRCodeAuthenticationToken(qrToken.getPrincipal(), qrToken.getCode(), qrToken.getConnectId(), user.getAuthorities());
            token.setDetails(authentication.getDetails());
            return token;
        } else if (authentication instanceof UsernamePasswordAuthenticationToken) {//用户名密码
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principal, authentication.getCredentials(), user.getAuthorities());
            token.setDetails(authentication.getDetails());
            return token;
        }
        return null;
    }

    @Override
    protected UserDetails retrieveUser(String principal, Authentication authentication) throws AuthenticationException {
        UserDetails loadedUser = null;
        TimingUserDetailService userDetailService;
        try {
            userDetailService = (TimingUserDetailService) getUserDetailsService();
            if (authentication instanceof TimingAuthenticationToken) {//用户名密码
                loadedUser = this.getUserDetailsService().loadUserByUsername(principal);
            } else if (authentication instanceof SmsCodeAuthenticationToken) {//手机验证码
                loadedUser = userDetailService.loadUserByPhone(principal);
            } else if (authentication instanceof ThirdOpenAuthenticationToken) {//第三方开放平台
                ThirdOpenAuthenticationToken thirdToken = (ThirdOpenAuthenticationToken) authentication;
                loadedUser = userDetailService.loadUserByThirdOpenCode(principal, thirdToken.getType());
            } else if (authentication instanceof QRCodeAuthenticationToken) {//二维码
                loadedUser = this.getUserDetailsService().loadUserByUsername(principal);
            } else if (authentication instanceof UsernamePasswordAuthenticationToken) {//用户名密码
                loadedUser = this.getUserDetailsService().loadUserByUsername(principal);
            }
        } catch (UsernameNotFoundException var6) {
            throw var6;
        } catch (Exception var7) {
            throw new InternalAuthenticationServiceException(var7.getMessage(), var7);
        }
        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
        } else {
            return loadedUser;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
//        return TimingAuthenticationToken.class.isAssignableFrom(authentication)
//                || UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)
//                || QRCodeAuthenticationToken.class.isAssignableFrom(authentication)
//                || SmsCodeAuthenticationToken.class.isAssignableFrom(authentication)
//                || ThirdOpenAuthenticationToken.class.isAssignableFrom(authentication);
        return TimingAuthenticationToken.class.isAssignableFrom(authentication)
                || UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}