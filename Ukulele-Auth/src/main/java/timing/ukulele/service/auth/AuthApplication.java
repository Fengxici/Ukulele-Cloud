package timing.ukulele.service.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author fengxici
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"timing.ukulele.facade.user", "timing.ukulele.facade.portal"})
public class AuthApplication {
//    @Bean
//    @LoadBalanced
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
