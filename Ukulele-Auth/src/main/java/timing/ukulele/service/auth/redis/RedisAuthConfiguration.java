package timing.ukulele.service.auth.redis;

import net.sf.jsqlparser.expression.UserVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import timing.ukulele.facade.portal.model.view.MenuVO;
import timing.ukulele.facade.user.model.view.UserVO;

/**
 * Redis配置类
 */
@Configuration
public class RedisAuthConfiguration {

    @Autowired
    private JedisConnectionFactory con;

    @Bean
    public RedisTemplate<String, UserVO> baseRoleTemplate() {
        RedisTemplate<String, UserVO> template = new RedisTemplate<>();
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
