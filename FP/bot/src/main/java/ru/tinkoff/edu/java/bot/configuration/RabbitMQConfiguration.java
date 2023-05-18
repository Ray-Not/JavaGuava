package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.configuration.records.ApplicationConfig;

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
        return new Queue(config.queue());
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(config.routingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}