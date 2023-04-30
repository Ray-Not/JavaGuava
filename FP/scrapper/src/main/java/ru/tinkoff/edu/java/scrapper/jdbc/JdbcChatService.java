package ru.tinkoff.edu.java.scrapper.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.scrapper.exceptions.customExceptions.*;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.ChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;

public class JdbcChatService implements LinkOperations, ChatOperations, LinkChatOperations {
    public void addChat(JdbcTemplate jdbc, long chat) {
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id != 0) {
            throw new EntryExsistException("Вы уже зарегистрированы");
        } else {
            i_addChat(jdbc, chat);
        }
    }

    public void removeChat(JdbcTemplate jdbc, long chat) {
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id != 0) {
            i_removeChat(jdbc, chat);
            i_removeLinkChatAllChat(jdbc, chat_id);
        } else throw new EntryNotExsistException("Пользователь не найден");
    }
}
