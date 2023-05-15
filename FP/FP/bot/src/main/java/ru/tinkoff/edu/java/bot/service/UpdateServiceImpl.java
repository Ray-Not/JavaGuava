package ru.tinkoff.edu.java.bot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.bot.dto.LinkUpdate;
import ru.tinkoff.edu.java.bot.telegram.Bot;

@Service
@Slf4j
public class UpdateServiceImpl implements UpdateService {

    private final Bot bot;

    public UpdateServiceImpl(Bot bot) {
        this.bot = bot;
    }

    public void updateLink(LinkUpdate linkUpdate) {
        log.info("updateLink() method invocation in UpdateServiceImpl");
        String message = "Вышло обновление по ссылке " + linkUpdate.url() + " \n" + linkUpdate.description();
        for (Long chatId : linkUpdate.tgChatIds()) {
            bot.sendMessage(chatId, message);
        }
    }

}