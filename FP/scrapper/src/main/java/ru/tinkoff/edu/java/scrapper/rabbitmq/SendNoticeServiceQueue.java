package ru.tinkoff.edu.java.scrapper.rabbitmq;

import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.model.LinkUpdate;

@Service
public class SendNoticeServiceQueue implements SendNoticeServiceImplement {
    private final ScrapperQueueProducer queueProducer;

    public SendNoticeServiceQueue(ScrapperQueueProducer queueProducer) {
        this.queueProducer = queueProducer;
    }

    @Override
    public void sendNotice(String message) {
        // Отправка сообщения в очередь через ScrapperQueueProducer
        LinkUpdate update = new LinkUpdate(message);
        queueProducer.send(update);
    }
}
