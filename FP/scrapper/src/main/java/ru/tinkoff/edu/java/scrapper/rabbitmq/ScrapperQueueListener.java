package ru.tinkoff.edu.java.scrapper.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import ru.tinkoff.edu.java.scrapper.model.LinkUpdate;

@RabbitListener(queues = "${app.queue}")
public class ScrapperQueueListener {
    private final HandleNoticeServiceQueue handleNoticeServiceQueue;

    public ScrapperQueueListener(HandleNoticeServiceQueue notificationHandler) {
        this.handleNoticeServiceQueue = notificationHandler;
    }

    @RabbitHandler
    public void receiver(LinkUpdate update) {
        handleNoticeServiceQueue.handleNotification(update.url());
    }
}
