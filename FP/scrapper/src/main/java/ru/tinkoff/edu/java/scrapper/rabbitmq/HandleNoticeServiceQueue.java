package ru.tinkoff.edu.java.scrapper.rabbitmq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HandleNoticeServiceQueue implements HandleNoticeServiceImplement {

    Logger log = LoggerFactory.getLogger(HandleNoticeServiceQueue.class);
    @Override
    public void handleNotification(String message) {
        log.info("Пришло уведомление с Rabbit очереди: " + message);
    }
}