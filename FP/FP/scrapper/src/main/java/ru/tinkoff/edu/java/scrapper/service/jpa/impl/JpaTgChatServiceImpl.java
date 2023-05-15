package ru.tinkoff.edu.java.scrapper.service.jpa.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.exception.ChatAlreadyExistException;
import ru.tinkoff.edu.java.scrapper.exception.ChatNotFoundException;
import ru.tinkoff.edu.java.scrapper.model.commonDto.User;
import ru.tinkoff.edu.java.scrapper.model.jpa.UserEntity;
import ru.tinkoff.edu.java.scrapper.repository.jpa.JpaUserRepository;
import ru.tinkoff.edu.java.scrapper.service.contract.TgChatService;

import java.util.Optional;


@Slf4j
public class JpaTgChatServiceImpl implements TgChatService {

    private final JpaUserRepository userRepository;

    public JpaTgChatServiceImpl(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        log.info("register() method invocation in JpaTgChatServiceImpl. User chatId = "+user.getChatId());
        Optional<UserEntity> optionalUser = userRepository.findById(user.getChatId());
        if (optionalUser.isPresent()) throw new ChatAlreadyExistException("Такой чат уже зарегистрирован!");
        userRepository.save(User.toEntity(user));
    }

    @Override
    public void unregister(Long chatId) {
        log.info("unregister() method invocation in JpaTgChatServiceImpl. User chatId = "+chatId);
        Optional<UserEntity> optionalUser = userRepository.findById(chatId);
        if (optionalUser.isEmpty()) throw new ChatNotFoundException("Такой чат не зарегистрирован!");
        userRepository.delete(optionalUser.get());
    }



}