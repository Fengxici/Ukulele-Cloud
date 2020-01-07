package timing.ukulele.service.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import timing.ukulele.service.auth.filter.TimingLoginAuthenticationFilter;
import timing.ukulele.service.auth.handler.TimingLoginAuthSuccessHandler;
import timing.ukulele.service.auth.provider.TimingAuthenticationProvider;
import timing.ukulele.service.auth.service.TimingUserDetailService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final TimingUserDetailService timingUserDetailService;

    @Autowired
    public WebSecurityConfig(TimingUserDetailService timingUserDetailService) {
        this.timingUserDetailService = timingUserDetailService;
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
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(timingAuthenticationProvider()));
        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/actuator/**", "/css/**", "/js/**", "/favicon.ico", "/webjars/**", "/images/**", "/static/**",
                "/hystrix.stream/**", "/jolokia", "/configprops", "/activiti", "/druid/**", "/oauth/deleteToken", "/backReferer");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(getTimingLoginAuthenticationFilter(), TimingLoginAuthenticationFilter.class)
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
    public BCryptPasswordEncoder myEncoder() {
        return new BCryptPasswordEncoder(6);
    }

    @Bean
    public TimingAuthenticationProvider timingAuthenticationProvider() {
        TimingAuthenticationProvider provider = new TimingAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(timingUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        provider.setPasswordEncoder(myEncoder());
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
