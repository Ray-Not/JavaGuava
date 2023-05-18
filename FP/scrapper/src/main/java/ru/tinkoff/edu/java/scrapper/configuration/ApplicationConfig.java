package ru.tinkoff.edu.java.scrapper.configuration;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.java.scrapper.rabbitmq.ScrapperQueueProducer;
import ru.tinkoff.edu.java.scrapper.rabbitmq.SendNoticeServiceHttp;
import ru.tinkoff.edu.java.scrapper.rabbitmq.SendNoticeServiceImplement;
import ru.tinkoff.edu.java.scrapper.rabbitmq.SendNoticeServiceQueue;
import ru.tinkoff.edu.java.scrapper.schedule.Scheduler;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
@EnableScheduling
public record ApplicationConfig(
        @NotNull String test,
        @NotNull Scheduler scheduler,
        @Value("${useQueue}") boolean useQueue
) {
@Bean
public SendNoticeServiceImplement notificationService(ScrapperQueueProducer queueProducer/*, BotClient botClient */) {
    if (useQueue) {
        return new SendNoticeServiceQueue(queueProducer);
    } else {
        /* Заглушка */
        return new SendNoticeServiceHttp();
    }
}
}
