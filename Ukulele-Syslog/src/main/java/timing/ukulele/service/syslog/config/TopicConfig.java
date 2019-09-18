package timing.ukulele.service.syslog.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import timing.ukulele.data.syslog.LogExchange;


@Configuration
public class TopicConfig {
    @Bean
    public Queue coreQueue() {
        return new Queue(LogExchange.SYS_LOG_ROUTING_KEY);
    }

    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange(LogExchange.SYS_LOG_EXCHANGE);
    }

    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        return BindingBuilder.bind(coreQueue).to(coreExchange).with(LogExchange.SYS_LOG_ROUTING_KEY);
    }
}
