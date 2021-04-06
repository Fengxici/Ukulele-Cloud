package timing.ukulele.service.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import timing.ukulele.redisson.cache.CacheManager;
import timing.ukulele.service.auth.filter.TimingLoginAuthenticationFilter;
import timing.ukulele.service.auth.handler.TimingLoginAuthSuccessHandler;
import timing.ukulele.service.auth.provider.QRCodeAuthenticationProvider;
import timing.ukulele.service.auth.provider.SmsCodeAuthenticationProvider;
import timing.ukulele.service.auth.provider.ThirdOpenAuthenticationProvider;
import timing.ukulele.service.auth.provider.TimingAuthenticationProvider;
import timing.ukulele.service.auth.service.TimingUserDetailService;

import java.util.Arrays;

/**
 * @author fengxici
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TimingUserDetailService timingUserDetailService;
    private final CacheManager cacheManager;

    @Autowired
    public WebSecurityConfig(TimingUserDetailService timingUserDetailService, CacheManager cacheManager) {
        this.timingUserDetailService = timingUserDetailService;
        this.cacheManager = cacheManager;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    /**
//     * 用户验证
//     *
//     * @param auth
//     */
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(timingAuthenticationProvider());
////        auth.authenticationProvider(daoAuthenticationProvider());
//    }

    //    与configure(AuthenticationManagerBuilder auth) 二选一
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() {
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(timingAuthenticationProvider(),
                smsCodeAuthenticationProvider(),
                qrCodeAuthenticationProvider(),
                thirdOpenAuthenticationProvider()));
        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/actuator/**", "/css/**", "/js/**", "/favicon.ico", "/webjars/**", "/images/**", "/static/**",
                "/hystrix.stream/**", "/jolokia", "/configprops", "/activiti", "/druid/**", "/oauth/deleteToken", "/backReferer", "/common/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterAt(getTimingLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 配置登陆页/login并允许访问
                .formLogin().loginPage("/login").permitAll()
                // 登出页
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/backReferer")
                // 其余所有请求全部需要鉴权认证
                .and().authorizeRequests().anyRequest().authenticated()
                // 由于使用的是JWT，我们这里不需要csrf
                .and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder globalEncoder() {
        return new BCryptPasswordEncoder(6);
    }

    /**
     * 用户名密码
     *
     * @return
     */
    @Bean
    public TimingAuthenticationProvider timingAuthenticationProvider() {
        TimingAuthenticationProvider provider = new TimingAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(timingUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        provider.setPasswordEncoder(globalEncoder());
        return provider;
    }

    /**
     * 手机验证码
     *
     * @return
     */
    @Bean
    public SmsCodeAuthenticationProvider smsCodeAuthenticationProvider() {
        SmsCodeAuthenticationProvider provider = new SmsCodeAuthenticationProvider(cacheManager, timingUserDetailService);
        return provider;
    }

    /**
     * 二维码
     *
     * @return
     */
    @Bean
    public QRCodeAuthenticationProvider qrCodeAuthenticationProvider() {
        QRCodeAuthenticationProvider provider = new QRCodeAuthenticationProvider(timingUserDetailService, cacheManager);
        return provider;
    }

    /**
     * 第三方开放平台
     *
     * @return
     */
    @Bean
    public ThirdOpenAuthenticationProvider thirdOpenAuthenticationProvider() {
        ThirdOpenAuthenticationProvider provider = new ThirdOpenAuthenticationProvider(timingUserDetailService);
        return provider;
    }

    @Bean
    public TimingLoginAuthenticationFilter getTimingLoginAuthenticationFilter() {
        TimingLoginAuthenticationFilter filter = new TimingLoginAuthenticationFilter();
        return (TimingLoginAuthenticationFilter) getFilter(filter);
    }

    private AbstractAuthenticationProcessingFilter getFilter(AbstractAuthenticationProcessingFilter filter) {
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(new TimingLoginAuthSuccessHandler());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        return filter;
    }

}
