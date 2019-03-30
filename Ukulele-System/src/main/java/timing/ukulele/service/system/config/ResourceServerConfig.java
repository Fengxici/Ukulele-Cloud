//package timing.ukulele.service.system.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.util.StringUtils;
//
//import java.util.Objects;

/**
 * 资源服务配置 安全校验在网关中统一处理，若没有网关则微服务（资源）之间相互调用应当开启
 *
 * @ EnableResourceServer 启用资源服务
 * @ EnableWebSecurity 启用web安全
 * @ EnableGlobalMethodSecurity 启用全局方法安全注解，就可以在方法上使用注解来对请求进行过滤
 */
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    @Autowired(required = false)
//    private RemoteTokenServices remoteTokenServices;
//
//    @Autowired
//    private OAuth2ClientProperties oAuth2ClientProperties;
//
//    @Bean
//    @Qualifier("authorizationHeaderRequestMatcher")
//    public RequestMatcher authorizationHeaderRequestMatcher() {
//        return new RequestHeaderRequestMatcher("Authorization");
//    }
//    @Bean
//    public TokenStore tokenStore() {
//        return new JwtTokenStore(jwtAccessTokenConverter());
//    }
//
//    //与授权服务器使用共同的密钥进行解析
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        converter.setSigningKey("123");
//        return converter;
//    }
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .exceptionHandling()
//                .and()
//                .headers()
//                .frameOptions()
//                .disable()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .requestMatcher(authorizationHeaderRequestMatcher());
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        super.configure(resources);
//        if (StringUtils.isEmpty(oAuth2ClientProperties.getClientId())) {
//            resources.resourceId(oAuth2ClientProperties.getClientId());
//        }
//        if (Objects.nonNull(remoteTokenServices)) {
//            resources.tokenServices(remoteTokenServices);
//        }
//    }
//}
