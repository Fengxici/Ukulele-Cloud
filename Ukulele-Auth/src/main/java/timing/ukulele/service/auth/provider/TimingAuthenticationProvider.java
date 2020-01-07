package timing.ukulele.service.auth.provider;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import timing.ukulele.service.auth.Constant;
import timing.ukulele.service.auth.token.TimingAuthenticationToken;

public class TimingAuthenticationProvider extends TimingAbstractUserDetailsAuthenticationProvider {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, TimingAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages.getMessage("TimingAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String type = authentication.getType();
            if (StringUtils.isEmpty(type)) {
                this.logger.debug("Authentication failed: verifyCode does not match stored value");
                throw new BadCredentialsException(this.messages.getMessage("PhoneAuthenticationProvider.badCredentials", "Bad type"));
            }
            String presentedPassword = authentication.getCredentials().toString();
            // 验证开始
            if (Constant.SPRING_SECURITY_RESTFUL_TYPE_PHONE.equals(type)) {
                // 手机验证码验证，调用公共服务查询后台验证码缓存： key 为authentication.getPrincipal()的value， 并判断其与验证码是否匹配,
                // 此处写死为 1000 TODO
                if (!"1000".equals(presentedPassword)) {
                    this.logger.debug("Authentication failed: verifyCode does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad verifyCode"));
                }
            } else if (Constant.SPRING_SECURITY_RESTFUL_TYPE_QR.equals(type)) {
                // 二维码只需要根据 qrCode 查询到用户即可，所以此处无需验证 TODO
            } else if (Constant.SPRING_SECURITY_RESTFUL_TYPE_THIRD.equals(type)) {
                //
                logger.info(authentication);
            } else {
                logger.info(authentication);
                // 用户名密码验证
                if (!this.passwordEncoder.matches(userDetails.getPassword(), presentedPassword)) {
                    this.logger.debug("Authentication failed: password does not match stored value");
                    throw new BadCredentialsException(this.messages.getMessage("MyAbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
                }
            }
        }
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, TimingAuthenticationToken authentication, UserDetails user) {
//        TimingAuthenticationToken result = new PhoneAuthenticationToken(principal, authentication.getCredentials(), "phone", user.getAuthorities());
        authentication.setDetails(authentication.getDetails());
        return authentication;
    }

    @Override
    protected UserDetails retrieveUser(String principal, TimingAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser;
        try {
            String username = principal;
            if (StringUtils.isNotEmpty(authentication.getType()))
                username = "#=#" + authentication.getType();
            if (authentication.getExtension() != null)
                username = "#=#" + authentication.getExtension();
            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
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
//        return TimingAuthenticationProvider.class.isAssignableFrom(authentication);
        return true;
    }


    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}