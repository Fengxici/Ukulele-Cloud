package timing.ukulele.service.syslog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"timing.ukulele.facade.portal"})
public class SyslogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SyslogApplication.class, args);
    }
}
