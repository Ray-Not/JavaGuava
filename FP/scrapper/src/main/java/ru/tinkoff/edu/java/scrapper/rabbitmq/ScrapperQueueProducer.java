package ru.tinkoff.edu.java.scrapper.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.api.model.LinkUpdate;

import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;

@Service
public class ScrapperQueueProducer {

    private final AmqpTemplate rabbitTemplate;

    private final ApplicationConfig config;


    public ScrapperQueueProducer(AmqpTemplate rabbitTemplate, ApplicationConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        this.config = config;
    }

    public void send(LinkUpdate update) {
        rabbitTemplate.convertAndSend(config.exchangeName(), config.routingKey(), update);
        System.out.println("Mess update: " + update);
    }
}
