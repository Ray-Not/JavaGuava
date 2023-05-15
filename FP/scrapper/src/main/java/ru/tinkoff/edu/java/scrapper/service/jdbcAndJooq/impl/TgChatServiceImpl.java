package ru.tinkoff.edu.java.scrapper.service.jdbcAndJooq.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.java.scrapper.exception.ChatAlreadyExistException;
import ru.tinkoff.edu.java.scrapper.model.commonDto.User;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.SubscriptionRepository;
import ru.tinkoff.edu.java.scrapper.repository.jdbcAndJooqContract.UserRepository;
import ru.tinkoff.edu.java.scrapper.service.contract.TgChatService;

@Slf4j
public class TgChatServiceImpl implements TgChatService {

    private final UserRepository userRepository;

    private final SubscriptionRepository subscriptionRepository;


    public TgChatServiceImpl(UserRepository userRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public void register(User user) {
        log.info("register() method invocation in TgChatServiceImpl. chatId = "+user.getChatId());
        User userInBd = userRepository.findByChatId(user.getChatId());
        if (userInBd != null) throw new ChatAlreadyExistException();
        userRepository.add(user);
    }

    @Override
    @Transactional
    public void unregister(Long chatId) {
        log.info("unregister() method invocation in TgChatServiceImpl. chatId = "+chatId);
        userRepository.remove(chatId);
        subscriptionRepository.removeAllByUser(chatId);
    }

}