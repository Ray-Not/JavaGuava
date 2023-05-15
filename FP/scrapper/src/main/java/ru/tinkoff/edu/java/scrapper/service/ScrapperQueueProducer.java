package ru.tinkoff.edu.java.scrapper.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.scrapper.dto.LinkUpdate;

@Slf4j
//бин регистрируется в конфиге, т.к. его не нужно инициализировать в случае общения по HTTP
public class ScrapperQueueProducer implements UpdateNotificationService {

    private final AmqpTemplate rabbitTemplate;

    private final ApplicationConfig config;


    public ScrapperQueueProducer(AmqpTemplate rabbitTemplate, ApplicationConfig config) {
        this.rabbitTemplate = rabbitTemplate;
        this.config = config;
    }

    public void updateLink(LinkUpdate update) {
        rabbitTemplate.convertAndSend(config.exchangeName(), config.routingKey(), update);
        log.info("UpdateMessage " + update + " has been sent");
    }
}