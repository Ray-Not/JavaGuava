package ru.tinkoff.edu.java.scrapper.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.edu.java.scrapper.rabbitmq.ScrapperQueueProducer;

@SpringBootApplication
public class RabbitMQConfiguration {

    private final ApplicationConfig config;

    public RabbitMQConfiguration(ApplicationConfig config) {
        this.config = config;
    }
    @Bean
    Queue queue() {
        return QueueBuilder.durable(config.queueName())
                .withArgument("x-dead-letter-exchange", config.exchangeName())
                .withArgument("x-dead-letter-routing-key", config.routingKey() + ".dlq")
                .build();
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(config.exchangeName());
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(config.routingKey());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public ScrapperQueueProducer scrapperQueueProducer(AmqpTemplate rabbitTemplate) {
        return new ScrapperQueueProducer(rabbitTemplate, config);
    }
}
