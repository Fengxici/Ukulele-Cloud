package timing.ukulele.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(2147483636)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/kaptcha/**",
                "/actuator/**",
                "/css/**",
                "/js/**",
                "/favicon.ico",
                "/webjars/**",
                "/images/**",
                "/static/**",
                "/hystrix.stream/**",
                "/jolokia",
                "/configprops",
                "/druid/**",
                "/oauth/deleteToken",
                "/backReferer",
                "/resources/**",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/api/**/v2/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                // 禁用 CSRF 跨站伪造请求，便于测试
                        csrf().disable()
                // 验证所有请求
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
