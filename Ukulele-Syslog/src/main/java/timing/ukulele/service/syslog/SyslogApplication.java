package timing.ukulele.service.syslog;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@MapperScan("timing.ukulele.service.syslog.mapper")
public class SyslogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SyslogApplication.class, args);
    }
}
