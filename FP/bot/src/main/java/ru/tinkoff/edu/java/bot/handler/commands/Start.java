package ru.tinkoff.edu.java.bot.handler.commands;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.EntryExsistException;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.ChatOperations;

public interface Start extends ChatOperations {
    
    default String start(JdbcTemplate jdbc, long chat) {
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id != 0) {
            return "Вы уже зарегистрированы";
        } else {
            i_addChat(jdbc, chat);
            return "Регистрация успешна, ваш tg_id = " + chat;
        }
    }
}

