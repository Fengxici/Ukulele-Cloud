package timing.ukulele.service.auth.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import timing.ukulele.data.portal.view.MenuVO;
import timing.ukulele.data.portal.view.RoleVO;

/**
 * Redis配置类
 * @author fengxici
 */
@Configuration
public class RedisAuthConfiguration {

    private final JedisConnectionFactory con;

    public RedisAuthConfiguration(JedisConnectionFactory con) {
        this.con = con;
    }

    @Bean
    public RedisTemplate<String, RoleVO> baseRoleTemplate() {
        RedisTemplate<String, RoleVO> template = new RedisTemplate<>();
        template.setConnectionFactory(con);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, MenuVO> baseModelTemplate() {
        RedisTemplate<String, MenuVO> template = new RedisTemplate<>();
        template.setConnectionFactory(con);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
