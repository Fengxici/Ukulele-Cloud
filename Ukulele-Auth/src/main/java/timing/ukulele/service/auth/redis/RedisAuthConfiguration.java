package timing.ukulele.service.auth.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import timing.ukulele.facade.portal.model.persistent.SysMenu;
import timing.ukulele.facade.portal.model.persistent.SysRole;

/**
 * Redis配置类
 */
@Configuration
public class RedisAuthConfiguration {

    @Autowired
    private JedisConnectionFactory con;

    @Bean
    public RedisTemplate<String, SysRole> baseRoleTemplate() {
        RedisTemplate<String, SysRole> template = new RedisTemplate<>();
        template.setConnectionFactory(con);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, SysMenu> baseModelTemplate() {
        RedisTemplate<String, SysMenu> template = new RedisTemplate<>();
        template.setConnectionFactory(con);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
