package timing.ukulele.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.RestTemplate;
import timing.ukulele.gateway.UkuleleOauth2Properties;
import timing.ukulele.gateway.handler.UkuleleAccessDeniedHandler;

import java.util.Base64;
import java.util.Map;
import java.util.Objects;

/**
 * 资源服务器配置
 *
 * @author fengxici
 */
@Configuration
@EnableResourceServer
@EnableConfigurationProperties(UkuleleOauth2Properties.class)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {


    private OAuth2WebSecurityExpressionHandler expressionHandler;
    private final UkuleleAccessDeniedHandler accessDeniedHandler;
    private final ResourceServerProperties resource;
    private final UkuleleOauth2Properties oauth2Properties;

    public ResourceServerConfiguration(UkuleleOauth2Properties oauth2Properties, UkuleleAccessDeniedHandler accessDeniedHandler, ResourceServerProperties resource) {
        this.oauth2Properties = oauth2Properties;
        this.accessDeniedHandler = accessDeniedHandler;
        this.resource = resource;
    }

    @Autowired
    public void setExpressionHandler(OAuth2WebSecurityExpressionHandler expressionHandler) {
        this.expressionHandler = expressionHandler;
    }

    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getKeyFromAuthorizationServer());
        return converter;
    }

    /**
     * 通过访问授权服务器获取非对称加密公钥 Key
     *
     * @return 公钥 Key
     */
    private String getKeyFromAuthorizationServer() {
        final RestTemplate keyUriRestTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        final String username = this.resource.getClientId();
        final String password = this.resource.getClientSecret();
        if (username != null && password != null) {
            final byte[] token = Base64.getEncoder().encode((username + ":" + password).getBytes());
            headers.add("Authorization", "Basic " + new String(token));
        }
        final HttpEntity<Void> request = new HttpEntity<>(headers);
        final String url = this.resource.getJwt().getKeyUri();
        return (String) Objects.requireNonNull(keyUriRestTemplate
                .exchange(url, HttpMethod.GET, request, Map.class).getBody())
                .get("value");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
        http.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        oauth2Properties.getUrlPermitAll().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest()
                .access("@permissionService.hasPermission(request, authentication)");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.expressionHandler(expressionHandler);
        resources.accessDeniedHandler(accessDeniedHandler);
    }

    /**
     * 配置解决 spring-security-oauth问题
     * https://github.com/spring-projects/spring-security-oauth/issues/730
     *
     * @param applicationContext ApplicationContext
     * @return OAuth2WebSecurityExpressionHandler
     */
    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    /**
     * 加密方式
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
