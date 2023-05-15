package ru.tinkoff.edu.java.scrapper.service.contract;

import ru.tinkoff.edu.java.scrapper.model.commonDto.User;

public interface TgChatService {

    void register(User user);

    void unregister(Long chatId);

}