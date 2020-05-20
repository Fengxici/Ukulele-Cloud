package timing.ukulele.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.MultipartConfigElement;

/**
 * @author fengxici
 */
@EnableZuulProxy
@SpringBootApplication
//@EnableResourceServer
@EnableOAuth2Sso
@EnableFeignClients(basePackages = {"timing.ukulele.facade.portal"})
@EnableSwagger2
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        //KB,MB
        factory.setMaxFileSize(DataSize.ofGigabytes(10));
        /// 设置总上传数据总大小
        factory.setMaxFileSize(DataSize.ofGigabytes(100));
        return factory.createMultipartConfig();
    }

}
