package timing.ukulele.service.syslog;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableFeignClients(basePackages = {"timing.ukulele.facade.portal"})
public class SyslogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SyslogApplication.class, args);
    }
}
