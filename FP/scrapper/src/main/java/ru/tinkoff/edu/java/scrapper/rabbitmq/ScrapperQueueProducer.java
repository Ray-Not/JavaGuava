package ru.tinkoff.edu.java.scrapper.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.configuration.RabbitMQConfiguration;
import ru.tinkoff.edu.java.bot.configuration.records.ApplicationConfig;
import ru.tinkoff.edu.java.bot.model.LinkUpdate;

@Service
public class ScrapperQueueProducer {
    private final RabbitTemplate rabbitTemplate;

    private final ApplicationConfig config;

    public ScrapperQueueProducer(RabbitTemplate rabbitTemplate, ApplicationConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        this.config = config;
    }

    public void send(LinkUpdate update) {
        String exchange = config.exchange(); // Имя обмена
        String routingKey = config.routingKey(); // Маршрутный ключ

        rabbitTemplate.convertAndSend(exchange, routingKey, update);
    }
}

