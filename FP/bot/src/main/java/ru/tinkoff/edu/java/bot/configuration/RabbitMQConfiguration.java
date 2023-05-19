package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.configuration.records.ApplicationConfig;

import java.util.Collections;

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
                .withArgument("x-dead-letter-exchange",config.queue() + ".dlq")
                .build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with(config.routingKey());
    }

    @Bean
    public DirectExchange dlqExchange() {
        return new DirectExchange(config.exchange() + ".dlq");
    }

    @Bean
    public Queue dlqQueue() {
        return QueueBuilder.durable(config.queue() + ".dlq").build();
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder.bind(dlqQueue())
                .to(dlqExchange())
                .with(config.routingKey());
    }
}