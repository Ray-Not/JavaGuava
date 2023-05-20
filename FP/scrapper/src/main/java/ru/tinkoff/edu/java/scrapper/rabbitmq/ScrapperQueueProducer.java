package ru.tinkoff.edu.java.scrapper.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.model.LinkUpdate;

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

        rabbitTemplate.convertAndSend(exchange, routingKey, update.url());
    }
}

