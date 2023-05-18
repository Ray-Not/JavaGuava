package ru.tinkoff.edu.java.bot.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.edu.java.bot.api.model.LinkUpdate;

import java.util.HashMap;
import java.util.Map;

@Configuration
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
    Queue deadLetterQueue() {
        return new Queue(config.queueName() + ".dlq", true);
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
    Binding dlqBinding(Queue deadLetterQueue, DirectExchange exchange) {
        return BindingBuilder.bind(deadLetterQueue).to(exchange).with(config.routingKey() + ".dlq");
    }

    @Bean
    public ClassMapper classMapper() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put("ru.tinkoff.edu.java.bot.api.model.LinkUpdate", LinkUpdate.class);

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("ru.tinkoff.edu.java.bot.api.model.*");
        classMapper.setIdClassMapping(mappings);
        return classMapper;
    }

    @Bean
    public MessageConverter jsonMessageConverter(ClassMapper classMapper) {
        Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
        jsonConverter.setClassMapper(classMapper);
        return jsonConverter;
    }


    @Bean
    public ScrapperQueueListener scrapperQueueListener(AmqpTemplate rabbitTemplate, UpdateService updateService) {
        return new ScrapperQueueListener(rabbitTemplate, updateService);
    }


}