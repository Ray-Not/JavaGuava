package ru.tinkoff.edu.java.bot.handler.commands;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.tinkoff.edu.java.bot.handler.DB;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.ChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkChatOperations;
import ru.tinkoff.edu.java.scrapper.jdbc.operations.LinkOperations;

public interface Untrack extends LinkOperations, LinkChatOperations, ChatOperations {
    default String untrack(JdbcTemplate jdbc, String link, long chat) {
        int link_id = i_findLink(jdbc, link);
        int chat_id = i_findChat(jdbc, chat);
        if (chat_id == 0) {
            return "Перед использованием нужно зарегистрироваться";
        }
        if (link_id != 0) {
            i_removeLink(jdbc, link);
            if (!i_findLinkChat(jdbc, link_id, chat_id)) {
                i_removeLinkChat(jdbc, link_id, chat_id);
            }
            return "Ссылка удалена";
        } else return "Ссылки не существует";
    }
}
