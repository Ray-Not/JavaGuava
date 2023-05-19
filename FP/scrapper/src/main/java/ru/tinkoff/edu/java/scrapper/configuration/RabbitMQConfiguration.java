package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.scrapper.model.LinkUpdate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfiguration {

    private final ApplicationConfig config;

    public RabbitMQConfiguration(ApplicationConfig config) {
        this.config = config;
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(config.exchange());
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable(config.queue())
                .withArgument("x-dead-letter-exchange", config.queue() + ".dlq")
                .build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(config.routingKey());
    }

    @Bean
    public ClassMapper classMapper(){
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("ru.tinkoff.edu.java.scrapper.model.LinkUpdate", LinkUpdate.class);

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.scrapper.model.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper){
        Jackson2JsonMessageConverter jsonConverter=new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("rabbitmq", 5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // Другие настройки, если необходимо

        return connectionFactory;
    }
}